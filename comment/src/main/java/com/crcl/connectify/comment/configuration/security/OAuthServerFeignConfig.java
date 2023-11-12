package com.crcl.connectify.comment.configuration.security;

import com.crcl.connectify.comment.configuration.properties.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import reactivefeign.client.ReactiveHttpRequestInterceptor;

public class OAuthServerFeignConfig {
    @Bean
    public ReactiveHttpRequestInterceptor requestInterceptor(RestTemplate restTemplate, OAuth2ClientProperties oAuth2ClientProperties) {
        return new Oauth2ClientTokenInterceptor(restTemplate, oAuth2ClientProperties);
    }
}
