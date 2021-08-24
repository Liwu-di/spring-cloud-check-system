package fun.liwudi.commonpart.helper;

import fun.liwudi.domain.constants.Constant;
import fun.liwudi.domain.dto.JsonResponse;
import org.springframework.stereotype.Component;

/**
 * @author 李武第
 */
@Component
public class JsonResponseHelper<T> {
    public JsonResponse getJsonResponse (String msg,int code){
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMsg(msg);
        jsonResponse.setCode(code);
        return jsonResponse;
    }

    public JsonResponse getJsonResponseWithData(String msg,int code,T data){
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setCode(code);
        jsonResponse.setMsg(msg);
        jsonResponse.setData(data);
        return jsonResponse;
    }

    public JsonResponse success(T data){
       return getJsonResponseWithData(Constant.SUCCESS,Constant.APPLICATION_SUCCESS,data);
    }

    public JsonResponse error(String msg){
        return getJsonResponse(msg,Constant.ERROR);
    }


}
