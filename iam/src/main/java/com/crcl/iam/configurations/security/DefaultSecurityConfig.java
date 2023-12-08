package com.crcl.iam.configurations.security;

import com.crcl.core.utils.EndpointsUtils;
import com.crcl.iam.configurations.props.SecurityProperties;
import com.crcl.iam.configurations.web.CorsCustomizer;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebFluxSecurity
@Configuration(proxyBeanMethods = false)
@AllArgsConstructor
public class DefaultSecurityConfig {
    private final CorsCustomizer corsCustomizer;
    private final SecurityProperties securityProperties;

    /**
     * Returns a customizer for configuring the authorization rules for HTTP requests.
     *
     * @return a Customizer object for configuring the authorization rules
     */
    @NotNull
    private static Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> getHttpRequestsCustomizer() {
        return registry -> registry
                .requestMatchers(EndpointsUtils.Permitted.SWAGGER_END_POINTS).permitAll()
                .requestMatchers(EndpointsUtils.Permitted.ACTUATOR_END_POINTS).permitAll()
                .requestMatchers(HttpMethod.POST, "/users/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/oauth2/**").permitAll()
                .requestMatchers("/authentication/login/**").permitAll()
                .requestMatchers("/authentication/register/**").permitAll()
                .requestMatchers("/authentication/roles/**", "/authentication/permissions/**").hasAnyRole("ADMIN")
                .anyRequest()
                .authenticated();
    }

    /**
     * Returns a SecurityFilterChain for configuring security in the application.
     *
     * @param http the HttpSecurity object for configuring security
     * @return a SecurityFilterChain for configuring security
     * @throws Exception if an error occurs while configuring security
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        this.corsCustomizer.corsCustomizer(http);
        http.authorizeHttpRequests(getHttpRequestsCustomizer())
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(loginConfigurer -> loginConfigurer
                        .loginPage(securityProperties.getLoginPage())
                        .failureForwardUrl(securityProperties.getFailureForwardUrl()))
                .oauth2ResourceServer(configurer -> configurer.jwt(Customizer.withDefaults()));

        return http.build();
    }
}

