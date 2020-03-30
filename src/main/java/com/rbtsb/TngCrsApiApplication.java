package com.rbtsb;

import com.rbtsb.repository.impl.MasterEntityRepositoryImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableDiscoveryClient
//@EnableEurekaClient
@EnableDiscoveryClient
//@EnableFeignClients("com.rbtsb")
@EnableJpaRepositories(basePackages = {"com.rbtsb.repository"},repositoryBaseClass = MasterEntityRepositoryImpl.class)
@ComponentScan("com.rbtsb")
public class TngCrsApiApplication extends SpringBootServletInitializer implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TngCrsApiApplication.class, args);
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
