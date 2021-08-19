package fun.liwudi.authservice;

import fun.liwudi.commonpart.interceptor.MybatisInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @author 李武第
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("fun.liwudi.authservice.mapper")
public class AuthServiceApplication {

    @Bean
    Interceptor interceptor(){
        return new MybatisInterceptor();
    }
    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

}
