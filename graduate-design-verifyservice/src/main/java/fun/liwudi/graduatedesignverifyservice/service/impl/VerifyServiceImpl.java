package fun.liwudi.graduatedesignverifyservice.service.impl;

import fun.liwudi.graduatedesignverifyservice.consant.Constant;
import fun.liwudi.graduatedesignverifyservice.domain.*;
import fun.liwudi.graduatedesignverifyservice.feign.CheckInfoFeign;
import fun.liwudi.graduatedesignverifyservice.feign.CompanyConfFeign;
import fun.liwudi.graduatedesignverifyservice.feign.CompanyUserFeign;
import fun.liwudi.graduatedesignverifyservice.feign.UserConfFeign;
import fun.liwudi.graduatedesignverifyservice.helper.GpsHelper;
import fun.liwudi.graduatedesignverifyservice.helper.JsonResponseHelper;
import fun.liwudi.graduatedesignverifyservice.service.VerifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * @author 李武第
 */
@Service
public class VerifyServiceImpl implements VerifyService {

    @Autowired
    private GpsHelper gpsHelper;

    @Autowired
    private JsonResponseHelper jsonResponseHelper;

    @Autowired
    private CompanyConfFeign companyConfFeign;

    @Autowired
    private UserConfFeign userConfFeign;

    @Autowired
    private CheckInfoFeign checkInfoFeign;

    @Autowired
    private CompanyUserFeign companyUserFeign;

    private static Logger logger = LoggerFactory.getLogger(VerifyServiceImpl.class);

    @Override
    public JsonResponse getCheckInfo(VerifyInfo verifyInfo) {
        CompanyConf companyConf = new CompanyConf();
        UserInfo userInfo = new UserInfo();
        UserConf userConf = new UserConf();
        userConf.setUserCode(verifyInfo.getUserCode());
        companyConf.setCompanyCode(companyUserFeign.selectByUserCode(userConf).getData().get(0).getCompanyCode());
        companyConf = companyConfFeign.select(companyConf).getData();
        if(gpsHelper.getDistance(
                Double.valueOf(verifyInfo.getCheckAreaY().toString()),
                Double.valueOf(verifyInfo.getCheckAreaX().toString()),
                Double.valueOf(companyConf.getAreaGpsY().toString()),
                Double.valueOf(companyConf.getAreaGpsX().toString())
        )< Constant.CHECK_ARRANGE
        && Objects.nonNull(verifyInfo.getCheckIp())
                /*companyConf.getAreaIp().split(" ")[0].equals(verifyInfo.getCheckIp().split(" ")[0])*/
        ){
            try {
                CheckInfo checkInfo = new CheckInfo();
                checkInfo.setCheckIp(verifyInfo.getCheckIp());
                checkInfo.setCheckAreaX(verifyInfo.getCheckAreaX());
                checkInfo.setCheckSuccess(0);
                checkInfo.setCheckDate(LocalDate.now().toString()+""+LocalTime.now().toString());
                checkInfo.setCompanyCode(companyConf.getCompanyCode());
                checkInfo.setCheckAreaY(verifyInfo.getCheckAreaY());
                checkInfo.setUserCode(verifyInfo.getUserCode());
                checkInfoFeign.check(checkInfo);
                return jsonResponseHelper.getJsonResponse(0,Constant.SUCCESS_INFO,gpsHelper.getDistance(
                        Double.valueOf(verifyInfo.getCheckAreaY().toString()),
                        Double.valueOf(verifyInfo.getCheckAreaX().toString()),
                        Double.valueOf(companyConf.getAreaGpsY().toString()),
                        Double.valueOf(companyConf.getAreaGpsX().toString())
                ));
            } catch (Exception e){
                logger.error(e.getMessage(),e);
                return jsonResponseHelper.getJsonResponse(1,Constant.ERROR_INFO,null);
            }
        }
        else {
            try {
                CheckInfo checkInfo = new CheckInfo();
                checkInfo.setCheckIp(verifyInfo.getCheckIp());
                checkInfo.setCheckAreaX(verifyInfo.getCheckAreaX());
                checkInfo.setCheckSuccess(1);
                checkInfo.setCheckDate(LocalDate.now().toString());
                checkInfo.setCompanyCode(companyConf.getCompanyCode());
                checkInfo.setCheckAreaY(verifyInfo.getCheckAreaY());
                checkInfo.setUserCode(verifyInfo.getUserCode());
                checkInfoFeign.check(checkInfo);
                return jsonResponseHelper.getJsonResponse(1,Constant.CHECK_ERROR_INFO,gpsHelper.getDistance(
                        Double.valueOf(verifyInfo.getCheckAreaY().toString()),
                        Double.valueOf(verifyInfo.getCheckAreaX().toString()),
                        Double.valueOf(companyConf.getAreaGpsY().toString()),
                        Double.valueOf(companyConf.getAreaGpsX().toString())
                ));
            } catch (Exception e){
                logger.error(e.getMessage(),e);
                return jsonResponseHelper.getJsonResponse(1,Constant.ERROR_INFO,null);
            }

        }
    }
}
