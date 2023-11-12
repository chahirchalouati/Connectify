package com.crcl.core.configuration;

import com.crcl.core.common.constant.DefaultScopes;
import com.crcl.core.configuration.properties.ApiProperties;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
public class CoreSwaggerConfiguration {

    private final ApiProperties apiProperties;

    @Bean
    public OpenAPI api() {
        log.info("Initializing OpenAPI information: {}", apiProperties);

        final License license = new License()
                .name(apiProperties.getLicenseName())
                .url(apiProperties.getLicenseUrl());

        final Contact contact = new Contact()
                .name(apiProperties.getContactName())
                .email(apiProperties.getContactEmail());

        final Info info = new Info()
                .title(apiProperties.getTitle())
                .description(apiProperties.getDescription())
                .version(apiProperties.getVersion())
                .termsOfService(apiProperties.getTermsOfService())
                .contact(contact)
                .license(license);

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }

    @Bean
    @ConditionalOnProperty({"api.authorizationUrl", "api.tokenUrl"})
    public SecurityScheme securityScheme() {
        final SecurityScheme securityScheme = new SecurityScheme();
        securityScheme.setType(SecurityScheme.Type.OAUTH2);

        final OAuthFlow clientCredentialsFlow = new OAuthFlow()
                .authorizationUrl(apiProperties.getAuthorizationUrl())
                .tokenUrl(apiProperties.getTokenUrl());

        final Scopes scopes = new Scopes();
        scopes.addString(DefaultScopes.OPENID, DefaultScopes.OPENID);
        clientCredentialsFlow.setScopes(scopes);

        final OAuthFlows flows = new OAuthFlows().clientCredentials(clientCredentialsFlow);
        securityScheme.setFlows(flows);
        securityScheme.setName("OAuth2");

        log.info("Initializing security schema for Swagger API/UI: {} {}", clientCredentialsFlow, apiProperties);

        return securityScheme;
    }
}