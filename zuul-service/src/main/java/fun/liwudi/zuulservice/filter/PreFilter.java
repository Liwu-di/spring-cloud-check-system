package fun.liwudi.zuulservice.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.sun.org.apache.bcel.internal.generic.DCMPG;
import fun.liwudi.zuulservice.domain.JsonResponse;
import fun.liwudi.zuulservice.domain.UserTokenInfo;
import fun.liwudi.zuulservice.helper.DecodeHelper;
import fun.liwudi.zuulservice.helper.JsonResponseHelper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import sun.misc.BASE64Decoder;
import sun.security.provider.MD5;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

/**
 * @author 李武第
 */
public class PreFilter extends ZuulFilter {

    public static Logger logger = LoggerFactory.getLogger(PreFilter.class);

    private JsonResponseHelper jsonResponseHelper = new JsonResponseHelper();

    private DecodeHelper decodeHelper = new DecodeHelper();
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        if(request.getRequestURL().toString().startsWith("/public")){
            return false;
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info(String.format("%s >>> %s",request.getMethod(),request.getRequestURL().toString()));
        Object accessToken = request.getParameter("token");
        Cookie[] cookies = request.getCookies();
        List<Cookie> cookieList = Arrays.asList(cookies);
        Cookie userToken = cookieList.stream().filter(cookie -> {
            return StringUtils.equals(cookie.getName(),"token")?true:false;
        }).findFirst().orElse(null);
        if(userToken == null || StringUtils.isBlank(userToken.getName())){
            logger.warn("token is null");
            try {
                throwException(ctx);
                return null;
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
            }
            throwException(ctx);
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        String plainText = null;
        try {
            plainText = new String(decoder.decodeBuffer(userToken.getValue()));
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }
        String[] tests = null;
        if(StringUtils.isNotBlank(plainText)){
            tests = plainText.split("\\.");
        }
        else {
            throwException(ctx);
            return null;
        }
        if(Objects.nonNull(tests) && StringUtils.equals(LocalDate.now().toString(),tests[1])){
            return null;
        }
        else {
            throwException(ctx);
            return null;
        }
    }

    private void throwException(RequestContext ctx){
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(200);
        try {
            ctx.getResponse().getWriter().write(JSON.toJSONString(jsonResponseHelper.getJsonResponse("token has error",401)));
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }
}
