package fun.liwudi.graduatedesignuserinfomanage.rest;

import fun.liwudi.graduatedesignuserinfomanage.constants.Constants;
import fun.liwudi.graduatedesignuserinfomanage.domain.CheckInfo;
import fun.liwudi.graduatedesignuserinfomanage.domain.JsonResponse;
import fun.liwudi.graduatedesignuserinfomanage.helper.JsonResponseHelper;
import fun.liwudi.graduatedesignuserinfomanage.service.ExportAndImportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 李武第
 */
@RestController
public class ExportAndImportRest {

    @Autowired
    private JsonResponseHelper jsonResponseHelper;

    @Autowired
    private ExportAndImportService exportAndImportService;

    private Logger logger = LoggerFactory.getLogger(ExportAndImportRest.class);

    @PostMapping("/importUserInfo")
    public JsonResponse importUserInfo(@RequestParam("file") MultipartFile file){
        try {
            exportAndImportService.importUserInfo(file);
            return jsonResponseHelper.getJsonResponse(
                    Constants.SUCCESS,0);
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            return jsonResponseHelper.getJsonResponse(
                    Constants.IMPORT_ERROR,1
            );
        }
    }

    @PostMapping("/importCompanyConf")
    public JsonResponse importCompanyConf(@RequestParam("file") MultipartFile file){
        try {
            exportAndImportService.importCompanyConf(file);
            return jsonResponseHelper.getJsonResponse(
                    Constants.SUCCESS,0);
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            return jsonResponseHelper.getJsonResponse(
                    Constants.IMPORT_ERROR,1
            );
        }
    }

    @GetMapping("/exportCheckInfo")
    public void exportCheckInfo(HttpServletRequest request,
                                HttpServletResponse response,
                                @ModelAttribute CheckInfo checkInfo){
        try {
            exportAndImportService.exportCheckInfo(checkInfo,request,response);
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }

}

