package fun.liwudi.graduatedesignuserinfomanage.service;

import fun.liwudi.graduatedesignuserinfomanage.domain.CheckInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
/**
 * @author 李武第
 */
public interface ExportAndImportService {

    /**
     * 导入用户信息
     * @param file
     */
    void importUserInfo (MultipartFile file);

    /**
     * 导出打卡信息
     * @param checkInfo
     * @param request
     * @param response
     */
    void exportCheckInfo(CheckInfo checkInfo,
                         HttpServletRequest request,
                         HttpServletResponse response);

}
