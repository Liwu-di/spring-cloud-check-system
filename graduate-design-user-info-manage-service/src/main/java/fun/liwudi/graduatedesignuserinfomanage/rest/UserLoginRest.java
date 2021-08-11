package fun.liwudi.graduatedesignuserinfomanage.rest;

import fun.liwudi.graduatedesignuserinfomanage.domain.JsonResponse;
import fun.liwudi.graduatedesignuserinfomanage.domain.UserLogin;
import fun.liwudi.graduatedesignuserinfomanage.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 李武第
 */
@CrossOrigin(origins = "*")
@RestController
public class UserLoginRest {

    @Autowired
    UserLoginService userLoginService;

    @PostMapping("/login")
    public JsonResponse login(@RequestBody UserLogin userLogin, HttpServletResponse response, HttpServletRequest request){
        return userLoginService.login(userLogin,response,request);
    }
}
