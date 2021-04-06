package fun.liwudi.graduatedesignuserinfomanage.rest;

import fun.liwudi.graduatedesignuserinfomanage.constants.Constants;
import fun.liwudi.graduatedesignuserinfomanage.domain.CheckInfo;
import fun.liwudi.graduatedesignuserinfomanage.domain.JsonResponse;
import fun.liwudi.graduatedesignuserinfomanage.domain.UserConf;
import fun.liwudi.graduatedesignuserinfomanage.helper.JsonResponseHelper;
import fun.liwudi.graduatedesignuserinfomanage.service.CheckInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Objects;

/**
 * @author 李武第
 */
@CrossOrigin(origins = "*")
@RestController
public class CheckInfoRest {

    @Autowired
    private JsonResponseHelper jsonResponseHelper;

    @Autowired
    private CheckInfoService checkInfoService;

    private static Logger logger = LoggerFactory.getLogger(CheckInfoRest.class);

    @PostMapping("/ckeck")
    public JsonResponse check(@RequestBody CheckInfo checkInfo){
        if(Objects.nonNull(checkInfo)
                && StringUtils.isNotBlank(checkInfo.getCheckIp())
                && StringUtils.isNotBlank(checkInfo.getCompanyCode())
                && StringUtils.isNotBlank(checkInfo.getUserCode())
                && Objects.nonNull(checkInfo.getCheckAreaX())
                && Objects.nonNull(checkInfo.getCheckAreaY())
                && Objects.nonNull(checkInfo.getCheckSuccess())
        ){
            try {
                checkInfoService.addCheckInfo(checkInfo);
                return jsonResponseHelper.getJsonResponse(Constants.SUCCESS,0);
            } catch (Exception e){
                logger.error(e.getMessage(),e);
                return jsonResponseHelper.getJsonResponse(Constants.SUCCESS,0);
            }
        }
        else {
            return jsonResponseHelper.getJsonResponse(Constants.INFO_EMPTY,1);
        }
    }

    @PostMapping("/selectOneCheckInfo")
    public JsonResponse selectOne(@RequestBody CheckInfo checkInfo){
        if(Objects.nonNull(checkInfo)){
            try {
                return jsonResponseHelper.getJsonResponseWithData(Constants.SUCCESS,0,checkInfoService.selectOneCheckInfo(checkInfo));
            } catch (Exception e){
                logger.error(e.getMessage(),e);
                return jsonResponseHelper.getJsonResponse(Constants.FAIL,1);
            }
        }
        else {
            return jsonResponseHelper.getJsonResponse(Constants.INFO_EMPTY,1);
        }
    }

    @PostMapping("/selectByCompanyCode")
    public JsonResponse selectByCompanyCode(@RequestBody CheckInfo checkInfo){
        if(Objects.nonNull(checkInfo) && StringUtils.isNotBlank(checkInfo.getCompanyCode())){
            try {
                return jsonResponseHelper.getJsonResponseWithData(Constants.SUCCESS,0,checkInfoService.selectCheckInfosByCompanyCode(checkInfo));
            } catch (Exception e){
                logger.error(e.getMessage(),e);
                return jsonResponseHelper.getJsonResponse(Constants.FAIL,1);
            }
        }
        else {
            return jsonResponseHelper.getJsonResponse(Constants.INFO_EMPTY,1);
        }
    }

    @PostMapping("/selectByUserCode")
    public JsonResponse selectByUserCode(@RequestBody CheckInfo checkInfo){
        if (Objects.nonNull(checkInfo) && StringUtils.isNotBlank(checkInfo.getUserCode())){
            try {
                return jsonResponseHelper.getJsonResponseWithData(Constants.SUCCESS,0,checkInfoService.selectCheckInfosByUserCode(checkInfo));
            } catch (Exception e){
                logger.error(e.getMessage(),e);
                return jsonResponseHelper.getJsonResponse(Constants.FAIL,1);
            }
        }
        else {
            return jsonResponseHelper.getJsonResponse(Constants.INFO_EMPTY,1);
        }
    }

    @PostMapping("/selectKey")
    public List<String> selectKey(@RequestBody UserConf userConf){
        return checkInfoService.getKey(userConf);
    }
}
