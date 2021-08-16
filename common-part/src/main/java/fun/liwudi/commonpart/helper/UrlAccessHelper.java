package fun.liwudi.commonpart.helper;

import com.alibaba.fastjson.JSONObject;
import fun.liwudi.domain.bean.AccessParameter;
import fun.liwudi.domain.constants.Constant;
import fun.liwudi.domain.dto.JsonResponse;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Objects;

/**
 * @author 李武第
 */
@Component
public class UrlAccessHelper {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private JsonResponseHelper jsonResponseHelper;

    private Logger logger = LoggerFactory.getLogger(UrlAccessHelper.class);

    public JsonResponse accessByUrlUseGet(@RequestBody AccessParameter accessParameter){
        URIBuilder uriBuilder = null;
        URI uri = null;
        try {
            uriBuilder = new URIBuilder(accessParameter.getUrl());
            if(!CollectionUtils.isEmpty(accessParameter.getParameter())){
                for(Map.Entry<String,String> kv : accessParameter.getParameter().entrySet()){
                    uriBuilder.addParameter(kv.getKey(),kv.getValue());
                }
            }
            uri = uriBuilder.build();
            if(Objects.isNull(uri)){
                return jsonResponseHelper.getJsonResponse(Constant.CANNOT_ACCESS_URL,Constant.ERROR);
            }
            if(!CollectionUtils.isEmpty(accessParameter.getHeaderParameter())){
                HttpHeaders headers = new HttpHeaders();
                for(Map.Entry<String,String> kv : accessParameter.getHeaderParameter().entrySet()){
                    headers.add(kv.getKey(),kv.getValue());
                }
                ResponseEntity<JsonResponse> response = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity(headers), JsonResponse.class);
                return response.getBody();
            }
            else {
                ResponseEntity<JsonResponse> responseEntity = restTemplate.getForEntity(uri, JsonResponse.class);
                return responseEntity.getBody();
            }

        } catch (URISyntaxException e) {
            logger.error(e.getMessage(),e);
            return jsonResponseHelper.getJsonResponse(Constant.CANNOT_ACCESS_URL,Constant.ERROR);
        }
    }

    public JsonResponse accessByUrlUsePost(@RequestBody AccessParameter accessParameter){
        try {
            if(!CollectionUtils.isEmpty(accessParameter.getHeaderParameter())){
                HttpHeaders headers = new HttpHeaders();
                for(Map.Entry<String,String> kv : accessParameter.getHeaderParameter().entrySet()){
                    headers.add(kv.getKey(),kv.getValue());
                }
                ResponseEntity<JsonResponse> response = restTemplate.exchange(accessParameter.getUrl(), HttpMethod.POST, new HttpEntity(accessParameter.getBodyParameter(),headers), JsonResponse.class);
                return response.getBody();
            }
            else {
                JsonResponse jsonResponse = restTemplate.postForObject(accessParameter.getUrl(),accessParameter.getBodyParameter(), JsonResponse.class);
                return jsonResponse;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return jsonResponseHelper.getJsonResponse(Constant.CANNOT_ACCESS_URL,Constant.ERROR);
        }
    }

    public JsonResponse accessByUrlUsePut(@RequestBody AccessParameter accessParameter){
        HttpHeaders headers = new HttpHeaders();
        if(!CollectionUtils.isEmpty(accessParameter.getHeaderParameter())){
            for(Map.Entry<String,String> kv : accessParameter.getHeaderParameter().entrySet()){
                headers.add(kv.getKey(),kv.getValue());
            }
        }
        ResponseEntity<JsonResponse> response = restTemplate.exchange(accessParameter.getUrl(), HttpMethod.PUT, new HttpEntity(accessParameter.getBodyParameter(), headers), JsonResponse.class);
        return response.getBody();
    }

    public JsonResponse accessByUrlUseDelete(@RequestBody AccessParameter accessParameter){
        HttpHeaders headers = new HttpHeaders();
        if(!CollectionUtils.isEmpty(accessParameter.getHeaderParameter())){
            for(Map.Entry<String,String> kv : accessParameter.getHeaderParameter().entrySet()){
                headers.add(kv.getKey(),kv.getValue());
            }
        }
        ResponseEntity<JsonResponse> response = restTemplate.exchange(accessParameter.getUrl(), HttpMethod.DELETE, new HttpEntity(accessParameter.getBodyParameter(), headers), JsonResponse.class);
        return response.getBody();
    }

}
