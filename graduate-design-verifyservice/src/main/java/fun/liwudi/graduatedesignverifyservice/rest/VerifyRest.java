package fun.liwudi.graduatedesignverifyservice.rest;

import fun.liwudi.graduatedesignverifyservice.consant.Constant;
import fun.liwudi.graduatedesignverifyservice.domain.JsonResponse;
import fun.liwudi.graduatedesignverifyservice.domain.VerifyInfo;
import fun.liwudi.graduatedesignverifyservice.helper.JsonResponseHelper;
import fun.liwudi.graduatedesignverifyservice.service.VerifyService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.jar.JarEntry;

/**
 * @author 李武第
 */
@RestController
public class VerifyRest {

    @Autowired
    private VerifyService verifyService;

    @Autowired
    private JsonResponseHelper jsonResponseHelper;

    private static Logger logger = LoggerFactory.getLogger(VerifyRest.class);

    @CrossOrigin(origins = "*")
    @PostMapping("/verify")
    public JsonResponse verify(@RequestBody VerifyInfo verifyInfo){
        if(Objects.nonNull(verifyInfo)){
            try {
                return verifyService.getCheckInfo(verifyInfo);
            } catch (Exception e){
                logger.error(e.getMessage(),e);
                return jsonResponseHelper.getJsonResponse(1,Constant.ERROR_INFO,null);
            }
        }
        else {
            return jsonResponseHelper.getJsonResponse(1, Constant.EMPTY_INFO,null);
        }
    }
}
