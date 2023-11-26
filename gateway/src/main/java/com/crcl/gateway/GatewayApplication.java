package com.crcl.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.gateway.filter.GatewayMetricsFilter;
import org.springframework.cloud.gateway.handler.predicate.PathRoutePredicateFactory;

@ConfigurationPropertiesScan("com.crcl.gateway.configurations")
@SpringBootApplication
public class GatewayApplication extends PathRoutePredicateFactory {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
