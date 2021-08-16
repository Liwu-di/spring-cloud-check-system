package fun.liwudi.commonpart.helper;

import fun.liwudi.domain.bean.AccessParameter;
import fun.liwudi.domain.constants.Constant;
import fun.liwudi.domain.dto.JsonResponse;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        } catch (URISyntaxException e) {
            logger.error(e.getMessage(),e);
        }

        if(Objects.nonNull(uri)){
            return restTemplate.getForObject(uri,JsonResponse.class);
        }
        else {
            return jsonResponseHelper.getJsonResponse(Constant.CANNOT_ACCESS_URL,Constant.ERROR);
        }
    }


}
