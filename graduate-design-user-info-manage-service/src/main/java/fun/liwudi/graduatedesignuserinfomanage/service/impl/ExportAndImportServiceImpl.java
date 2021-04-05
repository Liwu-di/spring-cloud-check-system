package fun.liwudi.graduatedesignuserinfomanage.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import fun.liwudi.graduatedesignuserinfomanage.domain.CheckInfo;
import fun.liwudi.graduatedesignuserinfomanage.domain.CheckInfoExport;
import fun.liwudi.graduatedesignuserinfomanage.domain.UserInfoImport;
import fun.liwudi.graduatedesignuserinfomanage.listener.UserInfoListener;
import fun.liwudi.graduatedesignuserinfomanage.mapper.CheckInfoMapper;
import fun.liwudi.graduatedesignuserinfomanage.mapper.UserCompanyMapper;
import fun.liwudi.graduatedesignuserinfomanage.mapper.UserManageMapper;
import fun.liwudi.graduatedesignuserinfomanage.service.ExportAndImportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * @author 李武第
 */
@Service
public class ExportAndImportServiceImpl implements ExportAndImportService {

    @Autowired
    private UserCompanyMapper userCompanyMapper;

    @Autowired
    private UserManageMapper userManageMapper;

    @Autowired
    private CheckInfoMapper checkInfoMapper;

    private Logger logger = LoggerFactory.getLogger(ExportAndImportServiceImpl.class);

    @Override
    public void importUserInfo(MultipartFile file) {
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(file.getInputStream(), UserInfoImport.class, new UserInfoListener(userManageMapper,userCompanyMapper)).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
    }

    @Override
    public void exportCheckInfo(CheckInfo checkInfo, HttpServletRequest request, HttpServletResponse response) {

        try {
            OutputStream outputStream = response.getOutputStream();
            //添加响应头信息
            response.setHeader("Content-Disposition", "attachment; filename=".
                    concat("exportUser-").
                    concat(LocalDate.now().toString()).
                    concat("-").
                    concat(String.valueOf(LocalTime.now().getHour())).
                    concat(String.valueOf(LocalTime.now().getMinute())).
                    concat(String.valueOf(LocalTime.now().getSecond())).
                    concat(".xlsx"));
            response.setContentType(MediaType.MULTIPART_FORM_DATA_VALUE);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            List<CheckInfo> checkInfos = checkInfoMapper.selectCheckInfosByCompanyCode(checkInfo);
            List<CheckInfoExport> checkInfoExports =new ArrayList<>();
            checkInfos.stream().forEach(checkInfoItem ->{
                CheckInfoExport checkInfoExport = new CheckInfoExport();
                BeanUtils.copyProperties(checkInfoItem,checkInfoExport);
                if(Objects.equals(checkInfoItem.getCheckSuccess(),1)){
                    checkInfoExport.setCheckSuccess("失败");
                }
                else {
                    checkInfoExport.setCheckSuccess("成功");
                }
                checkInfoExports.add(checkInfoExport);
            });
            ExcelWriterBuilder writerBuilder = EasyExcel.write(outputStream, CheckInfoExport.class);
            ExcelWriterSheetBuilder writerSheetBuilder = writerBuilder.sheet("exportUser");
            writerSheetBuilder.doWrite(checkInfoExports);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                response.getOutputStream().close();
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }
        }
    }
}