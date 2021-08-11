package fun.liwudi.graduatedesignuserinfomanage.service;


import fun.liwudi.graduatedesignuserinfomanage.domain.JsonResponse;
import fun.liwudi.graduatedesignuserinfomanage.domain.UserLogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 李武第
 */
public interface UserLoginService {
    /**
     * user log in judgement
     * @param userLogin
     * @return
     */
    JsonResponse login(UserLogin userLogin, HttpServletResponse response, HttpServletRequest request);
}
