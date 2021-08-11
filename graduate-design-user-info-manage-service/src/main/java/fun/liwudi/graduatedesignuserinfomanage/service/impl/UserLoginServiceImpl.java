package fun.liwudi.graduatedesignuserinfomanage.service.impl;

import com.alibaba.fastjson.JSON;
import fun.liwudi.graduatedesignuserinfomanage.constants.Constants;
import fun.liwudi.graduatedesignuserinfomanage.domain.JsonResponse;
import fun.liwudi.graduatedesignuserinfomanage.domain.UserLogin;
import fun.liwudi.graduatedesignuserinfomanage.domain.UserTokenInfo;
import fun.liwudi.graduatedesignuserinfomanage.helper.DecodeHelper;
import fun.liwudi.graduatedesignuserinfomanage.helper.JsonResponseHelper;
import fun.liwudi.graduatedesignuserinfomanage.mapper.UserLoginMapper;
import fun.liwudi.graduatedesignuserinfomanage.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author 李武第
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    UserLoginMapper userLoginMapper;

    @Autowired
    JsonResponseHelper jsonResponseHelper;

    @Autowired
    private DecodeHelper decodeHelper;

    @Override
    public JsonResponse login(UserLogin userLogin, HttpServletResponse response, HttpServletRequest request) {
        if(Objects.nonNull(userLoginMapper.getUserInfoByIndex(userLogin))){
            String info;
            BASE64Encoder encoder = new BASE64Encoder();
            info = userLogin.getUserCode().concat(".").concat(LocalDate.now().toString());
            Cookie token = new Cookie("token",encoder.encode(info.getBytes()));
            response.addCookie(token);
            return jsonResponseHelper.getJsonResponse(Constants.SUCCESS,0);
        }
        else {
            return jsonResponseHelper.getJsonResponse(Constants.FAIL,1);
        }

    }
}
