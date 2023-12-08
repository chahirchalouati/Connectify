package com.crcl.core.configuration;

import com.crcl.core.configuration.properties.ApiProperties;
import com.crcl.core.exceptions.CommonGlobalHandlerException;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * The CommonApplicationConfiguration class is responsible for configuring common application beans and dependencies.
 * It is a configuration class annotated with @Configuration and imports required dependencies using @Import annotation.
 *
 * @see ApiProperties
 * @see CoreSwaggerConfiguration
 * @see CommonGlobalHandlerException
 */
@Import({
        ApiProperties.class,
        CoreSwaggerConfiguration.class,
        CommonGlobalHandlerException.class
})
@Configuration
public class CommonApplicationConfiguration {
}
