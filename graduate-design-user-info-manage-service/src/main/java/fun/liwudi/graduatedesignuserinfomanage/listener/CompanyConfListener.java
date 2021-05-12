package fun.liwudi.graduatedesignuserinfomanage.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import fun.liwudi.graduatedesignuserinfomanage.domain.*;
import fun.liwudi.graduatedesignuserinfomanage.mapper.CompanyConfMapper;
import fun.liwudi.graduatedesignuserinfomanage.mapper.UserCompanyMapper;
import fun.liwudi.graduatedesignuserinfomanage.mapper.UserManageMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 李武第
 */
public class CompanyConfListener extends AnalysisEventListener<CompanyConfImport> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 1000;
    List<CompanyConfImport> list = new ArrayList<CompanyConfImport>();
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private CompanyConfImport companyConfImport;

    private UserCompanyMapper userCompanyMapper;

    private CompanyConfMapper companyConfMapper;

    public CompanyConfListener(CompanyConfMapper companyConfMapper, UserCompanyMapper userCompanyMapper){
        this.userCompanyMapper = userCompanyMapper;
        this.companyConfMapper = companyConfMapper;
    }


    @Override
    public void invoke(CompanyConfImport data, AnalysisContext context) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }
    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        list.clear();
        LOGGER.info("所有数据解析完成！");
    }

    private void saveData(){
        List<CompanyConf> companyConfs = new ArrayList<>();
        CompanyConf companyConf = new CompanyConf();
        list.stream().forEach(item ->{
            companyConf.setCompanyCode(item.getCompanyCode());
            if(Objects.isNull(companyConfMapper.selectCompanyConf(companyConf))){
                CompanyConf companyConf1 = new CompanyConf();
                BeanUtils.copyProperties(item,companyConf1);
                companyConf1.setAreaGpsX(BigDecimal.valueOf(Double.valueOf(item.getAreaGpsX())));
                companyConf1.setAreaGpsY(BigDecimal.valueOf(Double.valueOf(item.getAreaGpsY())));
                companyConfs.add(companyConf1);
            }
        });
        if(!CollectionUtils.isEmpty(companyConfs)){
            companyConfMapper.batchSave(companyConfs);
        }

    }
}
