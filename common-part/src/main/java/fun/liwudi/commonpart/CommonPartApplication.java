package fun.liwudi.commonpart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author 李武第
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class CommonPartApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonPartApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
