package fun.liwudi.authservice.rest;

import fun.liwudi.authservice.bean.esbean.EsUserInfo;
import fun.liwudi.authservice.service.es.UserInfoEsService;
import fun.liwudi.commonpart.helper.JsonResponseHelper;
import fun.liwudi.domain.dto.JsonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigInteger;

/**
 * @author 李武第
 */
@RestController
@Slf4j
public class UserInfoRest {

    @Resource
    private UserInfoEsService userInfoEsService;

    @Resource
    private JsonResponseHelper jsonResponseHelper;

    @PostMapping("/test")
    public JsonResponse test(){
        EsUserInfo esUserInfo = new EsUserInfo();
        for(int i =0 ; i<20 ;++i){
            esUserInfo.setId(BigInteger.valueOf(i));
            userInfoEsService.save(esUserInfo);
            log.info("save id = "+i+" info");
        }
        esUserInfo = EsUserInfo.builder()
                .userCode("lwd")
                .userName("lwd")
                .build();
        esUserInfo.setId(BigInteger.valueOf(100));
        userInfoEsService.save(esUserInfo);
        log.info("save 100");
        userInfoEsService.delete(EsUserInfo.builder().id(BigInteger.valueOf(3)).build());
        log.info(String.valueOf(userInfoEsService.count()));
        log.info(userInfoEsService.getAll().toString());
        log.info(userInfoEsService.getByCode("lwd").toString());
        log.info(userInfoEsService.getByName("lwd").toString());
        log.info(userInfoEsService.pageQuery(0,30,"lwd").toString());
        return jsonResponseHelper.success(null);
    }

}
