package fun.liwudi.graduatedesignuserinfomanage.rest;

import fun.liwudi.graduatedesignuserinfomanage.constants.Constants;
import fun.liwudi.graduatedesignuserinfomanage.domain.JsonResponse;
import fun.liwudi.graduatedesignuserinfomanage.domain.UserConf;
import fun.liwudi.graduatedesignuserinfomanage.helper.JsonResponseHelper;
import fun.liwudi.graduatedesignuserinfomanage.service.UserCompanyRelationService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author 李武第
 */
@CrossOrigin(origins = "*")
@RestController
public class UserCompanyRelationRest {

    @Autowired
    private JsonResponseHelper jsonResponseHelper;

    @Autowired
    private UserCompanyRelationService userCompanyRelationService;

    private static Logger logger = LoggerFactory.getLogger(UserCompanyRelationRest.class);

    @PostMapping("/addUserCompany")
    public JsonResponse add(@RequestBody UserConf userConf){
        if(Objects.nonNull(userConf)){
            try{
                userCompanyRelationService.addUserToCompany(userConf);
                return jsonResponseHelper.getJsonResponse(Constants.SUCCESS,0);
            } catch (Exception e){
                logger.error(e.getMessage(),e);
                return jsonResponseHelper.getJsonResponse(Constants.FAIL,1);
            }
        }
        else {
            return jsonResponseHelper.getJsonResponse(Constants.INFO_EMPTY,1);
        }
    }

    @PostMapping("/deleteUserCompany")
    public JsonResponse delete(@RequestBody UserConf userConf){
        if(Objects.nonNull(userConf)
                && StringUtils.isNotBlank(userConf.getUserCode())
                && StringUtils.isNotBlank(userConf.getCompanyCode())){
            try {
                userCompanyRelationService.deleteUserCompany(userConf);
                return jsonResponseHelper.getJsonResponse(Constants.SUCCESS,0);
            } catch (Exception e){
                logger.error(e.getMessage(),e);
                return jsonResponseHelper.getJsonResponse(Constants.FAIL,1);
            }
        }
        else {
            return jsonResponseHelper.getJsonResponse(Constants.INFO_EMPTY,1);
        }
    }

    @PostMapping("/selectUserCompany")
    public JsonResponse selectOne(@RequestBody UserConf userConf){
        if(Objects.nonNull(userConf)){
            try {

                return jsonResponseHelper.getJsonResponseWithData(Constants.SUCCESS,0,userCompanyRelationService.selectUserCompany(userConf));
            } catch (Exception e){
                logger.error(e.getMessage(),e);
                return jsonResponseHelper.getJsonResponse(Constants.FAIL,1);
            }
        }
        else {
            return jsonResponseHelper.getJsonResponse(Constants.INFO_EMPTY,1);
        }
    }

    @PostMapping("/selectUserCompanyByUserCode")
    public JsonResponse selectByUserCode(@RequestBody UserConf userConf){
        if(Objects.nonNull(userConf)
                && StringUtils.isNotBlank(userConf.getUserCode())){
            try {
                return jsonResponseHelper.getJsonResponseWithData(Constants.SUCCESS,0,userCompanyRelationService.selectByUserCode(userConf));
            } catch (Exception e){
                logger.error(e.getMessage(),e);
                return jsonResponseHelper.getJsonResponse(Constants.FAIL,1);
            }
        }
        else {
            return jsonResponseHelper.getJsonResponse(Constants.INFO_EMPTY,1);
        }
    }

    @PostMapping("/selectUserCompanyByComapnyCode")
    public JsonResponse selectByCompanyCode(@RequestBody UserConf userConf){
        if(Objects.nonNull(userConf)
                && StringUtils.isNotBlank(userConf.getCompanyCode())){
            try {
                return jsonResponseHelper.getJsonResponseWithData(Constants.SUCCESS,0,userCompanyRelationService.selectByCompanyCode(userConf));
            } catch (Exception e){
                logger.error(e.getMessage(),e);
                return jsonResponseHelper.getJsonResponse(Constants.FAIL,1);
            }
        }
        else {
            return jsonResponseHelper.getJsonResponse(Constants.INFO_EMPTY,1);
        }
    }

}
