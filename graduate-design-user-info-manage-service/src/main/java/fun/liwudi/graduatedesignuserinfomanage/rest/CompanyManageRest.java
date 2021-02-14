package fun.liwudi.graduatedesignuserinfomanage.rest;

import fun.liwudi.graduatedesignuserinfomanage.constants.Constants;
import fun.liwudi.graduatedesignuserinfomanage.domain.CompanyConf;
import fun.liwudi.graduatedesignuserinfomanage.domain.JsonResponse;
import fun.liwudi.graduatedesignuserinfomanage.helper.JsonResponseHelper;
import fun.liwudi.graduatedesignuserinfomanage.service.CompanyManageService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 李武第
 */
@CrossOrigin(origins = "*")
@RestController
public class CompanyManageRest {
    private static Logger logger = LoggerFactory.getLogger(CompanyManageRest.class);
    @Autowired
    CompanyManageService companyManageService;
    @Autowired
    JsonResponseHelper jsonResponseHelper;

    @PostMapping("/addCompany")
    public JsonResponse add(@RequestBody CompanyConf companyConf){
        if(Objects.nonNull(companyConf)
                && StringUtils.isNotBlank(companyConf.getCompanyCode())
                && StringUtils.isNotBlank(companyConf.getCompanyName())
                && StringUtils.isNotBlank(companyConf.getAreaIp())
                && Objects.nonNull(companyConf.getAreaGpsX())
                && Objects.nonNull(companyConf.getAreaGpsY())
        ){
            try{
                companyManageService.addCompanyConf(companyConf);
                return jsonResponseHelper.getJsonResponse(Constants.SUCCESS,0);
            }catch (Exception e){
                logger.error(e.getMessage(),e);
                return jsonResponseHelper.getJsonResponse(Constants.FAIL,1);
            }
        }else {
            return jsonResponseHelper.getJsonResponse(Constants.COMPANY_CODE_EMPTY,1);
        }
    }

    @PostMapping("/deleteCompany")
    public JsonResponse delete (@RequestBody CompanyConf companyConf){
        if(Objects.nonNull(companyConf) && StringUtils.isNotBlank(companyConf.getCompanyCode()) ){
            try {
                companyManageService.deleteCompanyConf(companyConf);
                return jsonResponseHelper.getJsonResponse(Constants.SUCCESS,0);
            }catch (Exception e){
                logger.error(e.getMessage(),e);
                return jsonResponseHelper.getJsonResponse(Constants.FAIL,1);
            }
        }else {
            return jsonResponseHelper.getJsonResponse(Constants.COMPANY_CODE_EMPTY,1);
        }
    }

    @PostMapping("/updateCompany")
    public JsonResponse update(@RequestBody CompanyConf companyConf){
        if(Objects.nonNull(companyConf) && StringUtils.isNotBlank(companyConf.getCompanyCode())){
            try {
                companyManageService.updateCompanyConf(companyConf);
                return jsonResponseHelper.getJsonResponse(Constants.SUCCESS,0);
            }catch (Exception e){
                logger.error(e.getMessage(),e);
                return jsonResponseHelper.getJsonResponse(Constants.FAIL,1);
            }
        }
        else {
            return jsonResponseHelper.getJsonResponse(Constants.COMPANY_CODE_EMPTY,1);
        }
    }

    @PostMapping("/selectCompany")
    public JsonResponse select(@RequestBody CompanyConf companyConf){
        try {
            CompanyConf query = companyManageService.selectCompanyConf(companyConf);
            return jsonResponseHelper.getJsonResponseWithData(Constants.SUCCESS, 0, query);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return jsonResponseHelper.getJsonResponse(Constants.FAIL,1);
        }
    }

    @PostMapping("/selectCompanys")
    public JsonResponse selects(@RequestBody CompanyConf companyConf){
        try {
            List<CompanyConf> query = companyManageService.selectCompanyConfs(companyConf);
            return jsonResponseHelper.getJsonResponseWithData(Constants.SUCCESS, 0, query);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return jsonResponseHelper.getJsonResponse(Constants.FAIL,1);
        }
    }



}
