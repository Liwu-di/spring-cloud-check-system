package fun.liwudi.graduatedesignuserinfomanage.mapper;

import fun.liwudi.graduatedesignuserinfomanage.domain.CompanyConf;
import fun.liwudi.graduatedesignuserinfomanage.domain.CompanyConfImport;
import fun.liwudi.graduatedesignuserinfomanage.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author 李武第
 */
@Mapper
public interface CompanyConfMapper {

    void addCompanyConf(CompanyConf companyConf);
    void deleteCompanyConf(CompanyConf companyConf);
    void updateCompanyConf(CompanyConf companyConf);
    CompanyConf selectCompanyConf(CompanyConf companyConf);
    List<CompanyConf> selectCompanyConfs(CompanyConf companyConf);
    void batchSave(List<CompanyConf> list);
}

