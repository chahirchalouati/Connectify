package com.crcl.iam;

import com.crcl.core.configuration.CommonApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@Import(CommonApplicationConfiguration.class)
@EnableReactiveFeignClients("com.crcl.iam.clients")
@ConfigurationPropertiesScan("com.crcl.iam.configurations.*")
@SpringBootApplication
@EnableAspectJAutoProxy
public class IdentityApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdentityApplication.class, args);
    }

}
