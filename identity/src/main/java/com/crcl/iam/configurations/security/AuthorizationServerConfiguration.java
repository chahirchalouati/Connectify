package com.crcl.iam.configurations.security;

import com.crcl.iam.configurations.props.SecurityProperties;
import com.crcl.iam.configurations.web.CorsCustomizer;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import java.util.List;

/**
 * The AuthorizationServerConfiguration class is responsible for configuring the authorization server settings
 * for the application. It defines the beans required for OAuth2 authentication and authorization.
 */
@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
public class AuthorizationServerConfiguration {

    private final CorsCustomizer corsCustomizer;
    private final SecurityProperties securityProperties;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Creates and configures the security filter chain for the OAuth2 authorization server.
     *
     * @param httpSecurity the HttpSecurity object to configure
     * @return the configured SecurityFilterChain object
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(httpSecurity);
        this.corsCustomizer.corsCustomizer(httpSecurity);
        httpSecurity.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(loginConfigurer -> loginConfigurer
                        .loginPage(securityProperties.getLoginPage())
                        .failureForwardUrl(securityProperties.getFailureForwardUrl()))
                .oauth2ResourceServer(configurer -> configurer.jwt(Customizer.withDefaults()))
                .build();
    }

    /**
     * Creates a new RegisteredClientRepository with the given PasswordEncoder and SecurityProperties.
     *
     * @param passwordEncoder    The PasswordEncoder used to encode client secrets.
     * @param securityProperties The SecurityProperties containing the client registration information.
     * @return A RegisteredClientRepository instance populated with the registered clients.
     */
    @Bean
    public RegisteredClientRepository registeredClientRepository(PasswordEncoder passwordEncoder, SecurityProperties securityProperties) {

        final List<RegisteredClient> registeredClients = securityProperties.getRegistrations().values().stream()
                .map(registration -> RegisteredClient.withId(registration.getId())
                        .clientId(registration.getId())
                        .clientSecret(passwordEncoder.encode(registration.getSecret()))
                        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                        .authorizationGrantTypes(types -> types.addAll(List.of(AuthorizationGrantType.CLIENT_CREDENTIALS, AuthorizationGrantType.AUTHORIZATION_CODE)))
                        .scopes(scopes -> scopes.addAll(registration.getScopes()))
                        .redirectUris(redirectUris -> redirectUris.addAll(registration.getUris()))
                        .build()).toList();
        return new InMemoryRegisteredClientRepository(registeredClients);
    }

    /**
     * Retrieves the provider settings for the authorization server.
     *
     * @return An instance of AuthorizationServerSettings that represents the provider settings.
     */
    @Bean
    public AuthorizationServerSettings providerSettings() {
        return AuthorizationServerSettings.builder().issuer(securityProperties.getIssuer()).build();
    }

    /**
     * Creates a JwtDecoder using the provided JWKSource.
     *
     * @param jwkSource the JWKSource to use for decoding JWT tokens
     * @return a JwtDecoder instance
     */
    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration
                .jwtDecoder(jwkSource);
    }


    /**
     * Creates and configures a CommonsRequestLoggingFilter to log request details.
     *
     * @return The configured CommonsRequestLoggingFilter.
     */
    @Profile("dev")
    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setMaxPayloadLength(64000);
        return loggingFilter;
    }
}
