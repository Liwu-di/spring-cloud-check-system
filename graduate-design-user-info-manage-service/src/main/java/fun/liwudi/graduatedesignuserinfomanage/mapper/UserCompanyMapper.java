package fun.liwudi.graduatedesignuserinfomanage.mapper;

import fun.liwudi.graduatedesignuserinfomanage.domain.UserConf;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;


/**
 * @author 李武第
 */
@Mapper
public interface UserCompanyMapper {

    void addUserToCompany (UserConf userConf);
    void deleteUserCompany (UserConf userConf);
    UserConf selectUserCompany (UserConf userConf);
    List<UserConf> selectByUserCode(UserConf userConf);
    List<UserConf> selectByCompanyCode(UserConf userConf);
    void batchSave(List<UserConf> list);

}
