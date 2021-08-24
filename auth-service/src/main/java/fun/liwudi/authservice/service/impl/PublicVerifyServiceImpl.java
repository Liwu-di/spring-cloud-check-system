package fun.liwudi.authservice.service.impl;

import fun.liwudi.authservice.bean.PublicVerifyDto;
import fun.liwudi.authservice.mapper.PublicVerifyMapper;
import fun.liwudi.authservice.service.PublicVerifyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author 李武第
 */
@Service
public class PublicVerifyServiceImpl implements PublicVerifyService {

    @Resource
    private PublicVerifyMapper publicVerifyMapper;

    @Override
    public Boolean isExistAk(PublicVerifyDto publicVerifyDto) {
        if(Objects.nonNull(publicVerifyMapper.findOne(publicVerifyDto))){
            return true;
        }
        else {
            return false;
        }
    }
}
