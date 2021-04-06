package fun.liwudi.graduatedesignuserinfomanage.service.impl;

import fun.liwudi.graduatedesignuserinfomanage.domain.CheckInfo;
import fun.liwudi.graduatedesignuserinfomanage.domain.UserConf;
import fun.liwudi.graduatedesignuserinfomanage.mapper.CheckInfoMapper;
import fun.liwudi.graduatedesignuserinfomanage.service.CheckInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 李武第
 */
@Service
public class CheckInfoServiceImpl implements CheckInfoService {

    @Autowired
    private CheckInfoMapper checkInfoMapper;

    @Override
    public void addCheckInfo(CheckInfo checkInfo) {
        checkInfoMapper.addCheckInfo(checkInfo);
    }

    @Override
    public CheckInfo selectOneCheckInfo(CheckInfo checkInfo) {
        return checkInfoMapper.selectOneCheckInfo(checkInfo);
    }

    @Override
    public List<CheckInfo> selectCheckInfosByUserCode(CheckInfo checkInfo) {
        return checkInfoMapper.selectCheckInfosByUserCode(checkInfo);
    }

    @Override
    public List<CheckInfo> selectCheckInfosByCompanyCode(CheckInfo checkInfo) {
        return checkInfoMapper.selectCheckInfosByCompanyCode(checkInfo);
    }

    @Override
    public List<String> getKey(UserConf userConf) {
        return checkInfoMapper.getKey(userConf);
    }
}
