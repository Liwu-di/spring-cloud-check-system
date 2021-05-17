package fun.liwudi.graduatedesignuserinfomanage.rest;

import fun.liwudi.graduatedesignuserinfomanage.constants.Constants;
import fun.liwudi.graduatedesignuserinfomanage.domain.JsonResponse;
import fun.liwudi.graduatedesignuserinfomanage.domain.Vocation;
import fun.liwudi.graduatedesignuserinfomanage.helper.JsonResponseHelper;
import fun.liwudi.graduatedesignuserinfomanage.service.VocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 李武第
 */
@RestController
public class VocationRest {

    @Autowired
    private VocationService vocationService;

    @Autowired
    private JsonResponseHelper jsonResponseHelper;

    private Logger logger = LoggerFactory.getLogger(VocationRest.class);

    @PostMapping("/vocation")
    public JsonResponse vocation(@RequestBody Vocation vocation){
        return vocationService.sendMail(vocation);
    }

    @GetMapping("/vocation/record")
    public JsonResponse record(@ModelAttribute Vocation vocation){
        try{
            vocationService.saveRecord(vocation);
            return jsonResponseHelper.getJsonResponse(Constants.SUCCESS,0);
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            return jsonResponseHelper.getJsonResponse("error",1);
        }

    }
}
