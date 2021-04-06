package fun.liwudi.graduatedesignverifyservice.feign;

import fun.liwudi.graduatedesignverifyservice.domain.JsonResponse;
import fun.liwudi.graduatedesignverifyservice.domain.UserConf;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

/**
 * @author 李武第
 */
@FeignClient("yier-design-graduate")
public interface CompanyUserFeign {
    @PostMapping("/selectUserCompany")
    JsonResponse<UserConf> selectOne(@RequestBody UserConf userConf);

    @PostMapping("/selectUserCompanyByUserCode")
    JsonResponse<java.util.List<UserConf>> selectByUserCode(@RequestBody UserConf userConf);

    @PostMapping("/selectKey")
    List<String> selectKey(UserConf userConf);
}
