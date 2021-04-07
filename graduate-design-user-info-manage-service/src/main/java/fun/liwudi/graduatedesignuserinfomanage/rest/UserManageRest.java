package fun.liwudi.graduatedesignuserinfomanage.rest;

import fun.liwudi.graduatedesignuserinfomanage.constants.Constants;
import fun.liwudi.graduatedesignuserinfomanage.domain.JsonResponse;
import fun.liwudi.graduatedesignuserinfomanage.domain.UserInfo;
import fun.liwudi.graduatedesignuserinfomanage.helper.JsonResponseHelper;
import fun.liwudi.graduatedesignuserinfomanage.service.UserManageService;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Mapper;
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
public class UserManageRest {

    private static Logger logger = LoggerFactory.getLogger(UserManageRest.class);

    @Autowired
    private JsonResponseHelper jsonResponseHelper;

    @Autowired
    private UserManageService userManageService;

    @PostMapping("/addUser")
    public JsonResponse add(@RequestBody UserInfo userInfo){
        if(Objects.nonNull(userInfo)){
            try {
                userManageService.addCompanyConf(userInfo);
                return jsonResponseHelper.getJsonResponse(Constants.SUCCESS,0);
            }catch (Exception e){
                logger.error(e.getMessage(),e);
                return jsonResponseHelper.getJsonResponse(Constants.FAIL,1);
            }
        }
        else {
            return jsonResponseHelper.getJsonResponse(Constants.INFO_EMPTY,1);
        }
    }

    @PostMapping("/deleteUser")
    public JsonResponse delete(@RequestBody UserInfo userInfo){
        if(Objects.nonNull(userInfo) && StringUtils.isNotBlank(userInfo.getUserCode())){
            try {
                userManageService.deleteUserInfo(userInfo);
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

    @PostMapping("/updateUser")
    public JsonResponse update(@RequestBody UserInfo userInfo){
        if (Objects.nonNull(userInfo) && StringUtils.isNotBlank(userInfo.getUserCode())){
            try {
                userManageService.updateUserInfo(userInfo);
                UserInfo userInfo1 = new UserInfo();
                userInfo1.setUserCode(userInfo.getUserCode());
                return jsonResponseHelper.getJsonResponseWithData(Constants.SUCCESS,0,userManageService.selectUserInfo(userInfo));
            }catch (Exception e){
                logger.error(e.getMessage(),e);
                return jsonResponseHelper.getJsonResponse(Constants.FAIL,1);
            }
        }
        else {
            return jsonResponseHelper.getJsonResponse(Constants.INFO_EMPTY,1);
        }
    }

    @PostMapping("/selectUser")
    public JsonResponse select(@RequestBody UserInfo userInfo){
        try {

            return jsonResponseHelper.getJsonResponseWithData(Constants.SUCCESS,0,userManageService.selectUserInfo(userInfo));
        } catch (Exception e){
            logger.error(e.getMessage(),e);
            return jsonResponseHelper.getJsonResponse(Constants.FAIL,1);
        }
    }

    @PostMapping("/selectUsers")
    public JsonResponse selects(@RequestBody UserInfo userInfo){
        try {
            return jsonResponseHelper.getJsonResponseWithData(Constants.SUCCESS,
                    0,userManageService.selectUserInfos(userInfo));
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return jsonResponseHelper.getJsonResponse(Constants.FAIL,1);
        }
    }

}
