package fun.liwudi.graduatedesignverifyservice.rest;

import fun.liwudi.graduatedesignverifyservice.consant.Constant;
import fun.liwudi.graduatedesignverifyservice.domain.*;
import fun.liwudi.graduatedesignverifyservice.helper.JsonResponseHelper;
import fun.liwudi.graduatedesignverifyservice.service.VerifyService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
        /**
         * todo:realize the function which obtain area info consulted from ip
         */
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

    @PostMapping("/verifyAdmin")
    public JsonResponse verifyAdmin(@RequestBody UserConf userConf){
        try{
            if(verifyService.verifyAdmin(userConf)){
                return jsonResponseHelper.getJsonResponse(0,Constant.SUCCESS_INFO,null);
            }
            else {
                return jsonResponseHelper.getJsonResponse(1,Constant.FAIL,null);
            }
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            return jsonResponseHelper.getJsonResponse(1,Constant.ERROR_INFO,null);
        }
    }

    @GetMapping("/verifyByBi")
    public Object verifyByBi(@RequestParam("userCode") String userCode,
                             @RequestParam("checkIp") String checkIp,
                             @RequestParam("checkAreaX") String checkAreaX,
                             @RequestParam("checkAreaY") String checkAreaY,
                             @RequestParam("biCode") String biCode) {
        VerifyInfo verifyInfo = new VerifyInfo();
        verifyInfo.setUserCode(userCode);
        verifyInfo.setCheckAreaX(BigDecimal.valueOf(Double.valueOf(checkAreaX)));
        verifyInfo.setCheckAreaY(BigDecimal.valueOf(Double.valueOf(checkAreaY)));
        verifyInfo.setCheckIp(checkIp);
        if (Objects.nonNull(verifyInfo)) {
            try {
                return verifyService.getCheckInfo(verifyInfo);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                return jsonResponseHelper.getJsonResponse(1, Constant.ERROR_INFO, null);
            }
        } else {
            return jsonResponseHelper.getJsonResponse(1, Constant.EMPTY_INFO, null);
        }
    }

    @PostMapping("/mapCheck")
    public JsonResponse mapCheck(@RequestBody MapCheck mapCheck){
        try {
            VerifyInfo verifyInfo = new VerifyInfo();
            BeanUtils.copyProperties(mapCheck,verifyInfo);
            verifyInfo.setCheckIp("地图打卡");
            verifyInfo.setCheckAreaX(BigDecimal.valueOf(Double.valueOf(mapCheck.getPosition().getLng())));
            verifyInfo.setCheckAreaY(BigDecimal.valueOf(Double.valueOf(mapCheck.getPosition().getLat())));
            JsonResponse jsonResponse = verify(verifyInfo);
            if(StringUtils.equals("0", String.valueOf(jsonResponse.getCode()))){
                return jsonResponseHelper.getJsonResponse(0,Constant.SUCCESS_INFO,jsonResponse.getData());
            }
            return jsonResponseHelper.getJsonResponse(1,Constant.FAIL,null);
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            return jsonResponseHelper.getJsonResponse(1,Constant.ERROR_INFO,null);
        }
    }
}
