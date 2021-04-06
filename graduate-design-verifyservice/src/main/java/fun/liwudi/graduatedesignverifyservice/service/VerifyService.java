package fun.liwudi.graduatedesignverifyservice.service;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import fun.liwudi.graduatedesignverifyservice.domain.JsonResponse;
import fun.liwudi.graduatedesignverifyservice.domain.UserConf;
import fun.liwudi.graduatedesignverifyservice.domain.VerifyInfo;

import java.math.BigDecimal;

/**
 * @author 李武第
 */
public interface VerifyService {

    JsonResponse getCheckInfo(VerifyInfo verifyInfo);

    Boolean verifyAdmin(UserConf userConf);

}
