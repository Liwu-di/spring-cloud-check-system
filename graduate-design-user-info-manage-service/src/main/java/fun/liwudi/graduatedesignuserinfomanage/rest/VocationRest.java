package fun.liwudi.graduatedesignuserinfomanage.rest;

import fun.liwudi.graduatedesignuserinfomanage.domain.JsonResponse;
import fun.liwudi.graduatedesignuserinfomanage.domain.Vocation;
import fun.liwudi.graduatedesignuserinfomanage.service.VocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李武第
 */
@RestController
public class VocationRest {

    @Autowired
    private VocationService vocationService;

    @PostMapping("/vocation")
    public JsonResponse vocation(@RequestBody Vocation vocation){
        return vocationService.sendMail(vocation);
    }
}
