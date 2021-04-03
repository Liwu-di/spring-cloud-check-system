package fun.liwudi.graduatedesignuserinfomanage.mapper;

import fun.liwudi.graduatedesignuserinfomanage.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 李武第
 */
@Mapper
public interface UserManageMapper {

    void addUserInfo(UserInfo userInfo);
    void deleteUserInfo(UserInfo userInfo);
    void updateUserInfo(UserInfo userInfo);
    UserInfo selectUserInfo(UserInfo userInfo);
    List<UserInfo> selectUserInfos(UserInfo userInfo);
    void batchSave(List<UserInfo> list);
    UserInfo selectUserInfoDeleted(UserInfo userInfo);
    
}
