package fun.liwudi.graduatedesignverifyservice.rest;

import fun.liwudi.graduatedesignverifyservice.consant.Constant;
import fun.liwudi.graduatedesignverifyservice.domain.JsonResponse;
import fun.liwudi.graduatedesignverifyservice.helper.JsonResponseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author 李武第
 */
@RestController
public class LwdLoveYiEr {

    private Logger logger = LoggerFactory.getLogger(LwdLoveYiEr.class);

    @Autowired
    private JsonResponseHelper jsonResponseHelper;

    @GetMapping("/love")
    public JsonResponse hello(){
        return jsonResponseHelper.getJsonResponse(0, Constant.SUCCESS_INFO,"别点啦，我是熠儿哒！");
    }
}
