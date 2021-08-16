package fun.liwudi.commonpart.rest;

import fun.liwudi.commonpart.helper.JsonResponseHelper;
import fun.liwudi.commonpart.helper.UrlAccessHelper;
import fun.liwudi.domain.bean.AccessParameter;
import fun.liwudi.domain.constants.Constant;
import fun.liwudi.domain.dto.JsonResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 李武第
 */
@RestController
public class TestRest {

    @Resource
    private JsonResponseHelper jsonResponseHelper;

    @Resource
    private UrlAccessHelper urlAccessHelper;

    @GetMapping("/testGetOut")
    public JsonResponse testGetOut(@RequestParam("key") String key){
        return jsonResponseHelper.getJsonResponseWithData(Constant.SUCCESS,Constant.CODE_SUCCESS,"ajfauriiehguierhighu".concat(key));
    }

    @PostMapping("/testPostOut")
    public JsonResponse testPostOut(@RequestBody AccessParameter accessParameter){
        return jsonResponseHelper.getJsonResponseWithData(Constant.SUCCESS,Constant.CODE_SUCCESS,"ajfauriiehguierhighu".concat(accessParameter.toString()));
    }

    @PostMapping("/getTest")
    public JsonResponse getTest(@RequestBody AccessParameter accessParameter){
        return urlAccessHelper.accessByUrlUseGet(accessParameter);
    }
}
