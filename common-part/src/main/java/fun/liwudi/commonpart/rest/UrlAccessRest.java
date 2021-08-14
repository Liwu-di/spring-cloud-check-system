package fun.liwudi.commonpart.rest;

import fun.liwudi.domain.bean.AccessParamater;
import fun.liwudi.domain.dto.JsonResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author 李武第
 */
@RestController
public class UrlAccessRest {

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/accessByUrlUseGet")
    public JsonResponse accessByUrlUseGet(@RequestBody AccessParamater accessParamater){
        return restTemplate.getForObject(accessParamater.getUrl(),JsonResponse.class);
    }
}
