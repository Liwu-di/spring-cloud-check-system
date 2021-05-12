package fun.liwudi.graduatedesignuserinfomanage.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import fun.liwudi.graduatedesignuserinfomanage.domain.CompanyConf;
import fun.liwudi.graduatedesignuserinfomanage.domain.UserConf;
import fun.liwudi.graduatedesignuserinfomanage.domain.UserInfo;
import fun.liwudi.graduatedesignuserinfomanage.domain.UserInfoImport;
import fun.liwudi.graduatedesignuserinfomanage.mapper.UserCompanyMapper;
import fun.liwudi.graduatedesignuserinfomanage.mapper.UserManageMapper;
import org.apache.catalina.User;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 李武第
 */
public class UserInfoListener extends AnalysisEventListener<UserInfoImport> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 1000;
    List<UserInfoImport> list = new ArrayList<UserInfoImport>();
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private UserInfoImport userInfoImport;

    private UserCompanyMapper userCompanyMapper;

    private UserManageMapper userManageMapper;

    public UserInfoListener(UserManageMapper userManageMapper, UserCompanyMapper userCompanyMapper){
        this.userCompanyMapper = userCompanyMapper;
        this.userManageMapper = userManageMapper;
    }


    @Override
    public void invoke(UserInfoImport data, AnalysisContext context) {
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
        List<UserInfo> userInfos = new ArrayList<>();
        List<UserConf> companyConfs = new ArrayList<>();
        list.stream().forEach(item ->{
            UserInfo userInfo = new UserInfo();
            UserConf userConf = new UserConf();
            userInfo.setUserCode(item.getUserCode());
            if(Objects.isNull(userManageMapper.selectUserInfo(userInfo))
                && Objects.isNull(userManageMapper.selectUserInfoDeleted(userInfo))){
                BeanUtils.copyProperties(item,userInfo);
                userInfo.setDeleteFlag("0");
                userInfos.add(userInfo);
            }

            BeanUtils.copyProperties(item,userConf);
            if(Objects.isNull(userCompanyMapper.selectUserCompany(userConf))){
                companyConfs.add(userConf);
            }

        });
        if(!CollectionUtils.isEmpty(userInfos)){
            userManageMapper.batchSave(userInfos);
        }
        if(!CollectionUtils.isEmpty(companyConfs)){
            userCompanyMapper.batchSave(companyConfs);
        }
    }
}
