package com.crcl.iam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@EnableReactiveFeignClients("com.crcl.iam.clients")
@ConfigurationPropertiesScan("com.crcl.iam.configurations.*")
@SpringBootApplication
@EnableAspectJAutoProxy
public class IamApplication {

    public static void main(String[] args) {
        SpringApplication.run(IamApplication.class, args);
    }

}
