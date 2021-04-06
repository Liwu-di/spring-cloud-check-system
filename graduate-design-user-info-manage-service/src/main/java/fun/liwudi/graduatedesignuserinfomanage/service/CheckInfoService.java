package fun.liwudi.graduatedesignuserinfomanage.service;

import fun.liwudi.graduatedesignuserinfomanage.domain.CheckInfo;
import fun.liwudi.graduatedesignuserinfomanage.domain.UserConf;

/**
 * @author 李武第
 */
public interface CheckInfoService {

    void addCheckInfo(CheckInfo checkInfo);
    CheckInfo selectOneCheckInfo(CheckInfo checkInfo);
    java.util.List<CheckInfo> selectCheckInfosByUserCode(CheckInfo checkInfo);
    java.util.List<CheckInfo> selectCheckInfosByCompanyCode(CheckInfo checkInfo);
    java.util.List<String> getKey(UserConf userConf);
}
