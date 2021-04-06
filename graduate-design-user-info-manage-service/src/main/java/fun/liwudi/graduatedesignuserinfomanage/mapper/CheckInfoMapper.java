package fun.liwudi.graduatedesignuserinfomanage.mapper;

import fun.liwudi.graduatedesignuserinfomanage.domain.CheckInfo;
import fun.liwudi.graduatedesignuserinfomanage.domain.UserConf;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author 李武第
 */
@Mapper
public interface CheckInfoMapper {

    void addCheckInfo(CheckInfo checkInfo);
    CheckInfo selectOneCheckInfo(CheckInfo checkInfo);
    java.util.List<CheckInfo> selectCheckInfosByUserCode(CheckInfo checkInfo);
    java.util.List<CheckInfo> selectCheckInfosByCompanyCode(CheckInfo checkInfo);
    java.util.List<String> getKey(UserConf userConf);
}
