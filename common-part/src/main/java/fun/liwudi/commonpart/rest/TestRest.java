package fun.liwudi.commonpart.rest;

import fun.liwudi.commonpart.helper.JsonResponseHelper;
import fun.liwudi.domain.bean.AccessParamater;
import fun.liwudi.domain.constants.Constant;
import fun.liwudi.domain.dto.JsonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 李武第
 */
@RestController
public class TestRest {

    @Resource
    private JsonResponseHelper jsonResponseHelper;

    @GetMapping("/testGetOut")
    public JsonResponse testGetOut(){
        return jsonResponseHelper.getJsonResponseWithData(Constant.SUCCESS,Constant.CODE_SUCCESS,"ajfauriiehguierhighu");
    }
}
