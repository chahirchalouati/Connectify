package com.crcl.core.configuration;

import com.crcl.core.configuration.properties.ApiProperties;
import com.crcl.core.exceptions.CommonGlobalHandlerException;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
        ApiProperties.class,
        CoreSwaggerConfiguration.class,
        CommonGlobalHandlerException.class
})
@Configuration
public class CommonApplicationConfiguration {
}
