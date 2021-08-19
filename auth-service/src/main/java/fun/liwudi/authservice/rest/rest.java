package fun.liwudi.authservice.rest;

import fun.liwudi.authservice.mapper.test;
import fun.liwudi.domain.bean.BaseDomain;
import fun.liwudi.domain.dto.JsonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 李武第
 */
@RestController
public class rest {

    @Resource
    private test test1;

    @GetMapping("test")
    public void test(){
        test1.test(new BaseDomain());
    }
}
