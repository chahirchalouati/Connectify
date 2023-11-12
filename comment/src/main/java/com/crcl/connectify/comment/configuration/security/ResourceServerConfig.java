package com.crcl.connectify.comment.configuration.security;

import com.crcl.connectify.comment.configuration.properties.OAuth2ClientResourceServerProperties;
import com.crcl.core.utils.EndpointsUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.client.RestTemplate;
import reactivefeign.ReactiveOptions;
import reactivefeign.webclient.WebReactiveOptions;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@RequiredArgsConstructor
public class ResourceServerConfig {

    private final CorsCustomizer corsCustomizer;

    @Bean
    public SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
        corsCustomizer.corsCustomizer(http);
        http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(
                        authorizeExchangeSpec -> authorizeExchangeSpec
                                .pathMatchers(EndpointsUtils.Permitted.ACTUATOR_END_POINTS).permitAll()
                                .pathMatchers(EndpointsUtils.Permitted.SWAGGER_END_POINTS).permitAll()
                                .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec.jwt(Customizer.withDefaults()));
        return http.build();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ReactiveOptions reactiveOptions() {
        return new WebReactiveOptions.Builder()
                .setReadTimeoutMillis(2000)
                .setWriteTimeoutMillis(2000)
                .setResponseTimeoutMillis(2000)
                .build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder(OAuth2ClientResourceServerProperties resourceServerProperties) {
        return ReactiveJwtDecoders.fromIssuerLocation(resourceServerProperties.getIssuerUri());
    }
}
