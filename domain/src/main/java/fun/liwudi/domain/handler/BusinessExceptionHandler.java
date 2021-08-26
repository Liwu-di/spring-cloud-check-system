package fun.liwudi.domain.handler;

import fun.liwudi.domain.constants.Constant;
import fun.liwudi.domain.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 李武第
 */
@ControllerAdvice
public class BusinessExceptionHandler {

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {}

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("author", "Magical Sam");
    }

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        Map map = new HashMap(4);
        map.put("code", Constant.ERROR);
        map.put("msg", ex.getMessage());
        return map;
    }

    /**
     * 拦截捕捉自定义异常 BusinessException.class
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    public Map myErrorHandler(BusinessException ex) {

        Map map = new HashMap(4);
        map.put("code", ex.getCode());
        map.put("msg", ex.getMsg());
        return map;

    }
}
