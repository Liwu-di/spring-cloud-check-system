package fun.liwudi.graduatedesignuserinfomanage.rest;

import fun.liwudi.graduatedesignuserinfomanage.constants.Constants;
import fun.liwudi.graduatedesignuserinfomanage.domain.JsonResponse;
import fun.liwudi.graduatedesignuserinfomanage.helper.JsonResponseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李武第
 */
public class LwdLoveYiEr {

    private static Logger logger = LoggerFactory.getLogger(LwdLoveYiEr.class);

    @Autowired
    private JsonResponseHelper jsonResponseHelper;

    @GetMapping("/love")
    public JsonResponse hello(){
        return jsonResponseHelper.getJsonResponseWithData(Constants.SUCCESS,0,"别点啦，熠儿是我哒！");
    }
}
