package com.crcl.iam;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@EnableReactiveFeignClients("com.crcl.iam.clients")
@EnableMongock
@EnableMongoAuditing
@ConfigurationPropertiesScan("com.crcl.iam.configuration.*")
@SpringBootApplication
public class IamApplication {

    public static void main(String[] args) {
        SpringApplication.run(IamApplication.class, args);
    }

}
