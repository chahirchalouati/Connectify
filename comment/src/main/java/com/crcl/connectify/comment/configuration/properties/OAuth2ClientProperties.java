package com.crcl.connectify.comment.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.notification-service")
@Data
public class OAuth2ClientProperties {
    private String clientId;
    private String clientSecret;
}