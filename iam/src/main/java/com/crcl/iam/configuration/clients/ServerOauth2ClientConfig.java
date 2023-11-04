package com.crcl.iam.configuration.clients;

import com.crcl.iam.configuration.clients.interceptors.Oauth2TokenInterceptorHelper;
import com.crcl.iam.configuration.clients.interceptors.ServiceOauth2TokenInterceptorHelper;
import com.crcl.iam.repository.MongoClientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerOauth2ClientConfig {
    @Bean
    public ServiceOauth2TokenInterceptorHelper serviceOauth2TokenInterceptorHelper(MongoClientRepository mongoClientRepository,
                                                                                   Oauth2TokenInterceptorHelper oauth2TokenInterceptorHelper) {
        return new ServiceOauth2TokenInterceptorHelper(mongoClientRepository, oauth2TokenInterceptorHelper);
    }

}
