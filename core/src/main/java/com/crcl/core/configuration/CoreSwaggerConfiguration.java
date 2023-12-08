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

/**
 * The CoreSwaggerConfiguration class is responsible for configuring the Swagger API documentation.
 * It initializes the OpenAPI object with API properties such as title, description, version, etc.
 * It also creates a SecurityScheme object for Swagger API and UI.
 *
 * @see ApiProperties
 */
@Slf4j
@RequiredArgsConstructor
public class CoreSwaggerConfiguration {

    /**
     * Represents the API properties used for configuring the API information,
     * such as title, description, version, etc.
     */
    private final ApiProperties apiProperties;

    /**
     * Creates and initializes an instance of {@link OpenAPI} with the provided API properties.
     * This method is responsible for configuring the OpenAPI information including title, description,
     * version, terms of service, contact, and license.
     *
     * @return The initialized {@link OpenAPI} instance.
     */
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

    /**
     * Creates and initializes a SecurityScheme object for Swagger API and UI.
     * The SecurityScheme object represents the security definition for the API.
     * This method is annotated with @Bean to indicate that it should be registered and managed as a bean by Spring.
     * The SecurityScheme bean will only be created if both api.authorizationUrl and api.tokenUrl properties are present.
     *
     * @return the initialized SecurityScheme object
     */
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
