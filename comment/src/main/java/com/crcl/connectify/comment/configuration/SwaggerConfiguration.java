package com.crcl.connectify.comment.configuration;

import com.crcl.core.configuration.CoreSwaggerConfiguration;
import com.crcl.core.configuration.properties.ApiProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({ApiProperties.class})
@Configuration
public class SwaggerConfiguration {
    @Bean
    public CoreSwaggerConfiguration coreSwaggerConfiguration(ApiProperties apiProperties){
        return new CoreSwaggerConfiguration(apiProperties);
    }
}
