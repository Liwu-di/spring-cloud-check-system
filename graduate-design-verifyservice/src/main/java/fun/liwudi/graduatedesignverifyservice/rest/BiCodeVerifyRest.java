package fun.liwudi.graduatedesignverifyservice.rest;

import fun.liwudi.graduatedesignverifyservice.consant.Constant;
import fun.liwudi.graduatedesignverifyservice.domain.BiCode;
import fun.liwudi.graduatedesignverifyservice.domain.UserConf;
import fun.liwudi.graduatedesignverifyservice.domain.UserInfo;
import fun.liwudi.graduatedesignverifyservice.feign.CompanyUserFeign;
import fun.liwudi.graduatedesignverifyservice.helper.JsonResponseHelper;
import fun.liwudi.graduatedesignverifyservice.helper.RedisKeyHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
/**
 * @author 李武第
 */
@RestController
public class BiCodeVerifyRest {

    @Autowired
    private RedisKeyHelper redisKeyHelper;

    @Autowired
    private CompanyUserFeign companyUserFeign;

    @Autowired
    private JsonResponseHelper jsonResponseHelper;

    @PostMapping("/getBiCode")
    public Object getBiCode(@RequestBody BiCode code){
        UserConf userConf = new UserConf();
        userConf.setUserCode(code.getUserCode());
        userConf = companyUserFeign.selectOne(userConf).getData();
        List list = companyUserFeign.selectKey(userConf);
        if(list.contains(code.getSpecialCode())){
            return jsonResponseHelper.getJsonResponse(0,Constant.SUCCESS_INFO,redisKeyHelper.generateMealNo(userConf));
        }
        else {
            return jsonResponseHelper.getJsonResponse(1,Constant.ERROR_INFO,null);
        }
    }

}
