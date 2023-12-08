package com.crcl.core.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Represents the API properties used for configuring the API information,
 * such as title, description, version, etc.
 */
@ConfigurationProperties(prefix = "api")
@Configuration
@Data
public class ApiProperties {
    private String title;
    private String description;
    private String version;
    private String termsOfService;
    private String contactName;
    private String contactEmail;
    private String licenseName;
    private String licenseUrl;
    private String authorizationUrl;
    private String tokenUrl;
}

