package fun.liwudi.authservice.interceptor;

import fun.liwudi.authservice.bean.PublicVerifyDto;
import fun.liwudi.authservice.service.PublicVerifyService;
import fun.liwudi.commonpart.helper.JsonResponseHelper;
import fun.liwudi.domain.constants.Constant;
import fun.liwudi.domain.dto.JsonResponse;
import fun.liwudi.domain.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author 李武第
 */
@Aspect
@Component
@Slf4j
public class PublicAuthInterceptor {

    @Resource
    private PublicVerifyService publicVerifyService;

    @Pointcut("@annotation(fun.liwudi.commonpart.annotation.PublicAK)")
    public void authPointCut(){}

    @Before("authPointCut()")
    public void publicAuthInterceptor(JoinPoint pjp) throws Exception {

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        //获取被拦截的方法
        Method method = signature.getMethod();
        //获取被拦截的方法名
        String methodName = method.getName();
        log.info(LocalDateTime.now().toString().concat("拦截方法：").concat(methodName));
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        Object result = null;
        Enumeration<String> enumeration = request.getHeaderNames();
        Map<String,String> headers = new LinkedHashMap<>();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            headers.put(name,value);
        }
        if(!CollectionUtils.contains(enumeration,Constant.PUBLIC_NAME)
        || !CollectionUtils.contains(enumeration,Constant.PUBLIC_PASS)
        || StringUtils.isBlank(headers.get(Constant.PUBLIC_PASS))
        || StringUtils.isBlank(headers.get(Constant.PUBLIC_NAME))){
            throw new BusinessException("该操作需要AK账号密码");
        }
        else {
            PublicVerifyDto publicVerifyDto = PublicVerifyDto.builder()
                    .name(headers.get(Constant.PUBLIC_NAME))
                    .pass(headers.get(Constant.PUBLIC_PASS))
                    .build();
            if(!publicVerifyService.isExistAk(publicVerifyDto)){
                throw new BusinessException("AK账号密码错误");
            }
        }
    }
}
