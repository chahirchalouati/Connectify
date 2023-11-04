package com.crcl.authentication.service.impl;

import com.crcl.authentication.configuration.props.SecurityProperties;
import com.crcl.authentication.domain.Client;
import com.crcl.core.service.generic.Enhancer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Objects;

@Component("clientSettingsEnhancer")
@AllArgsConstructor
@Slf4j
public class ClientSettingsEnhancer implements Enhancer<Client> {
    private final SecurityProperties securityProperties;

    @Override
    public Client enhance(final Client client) {
        var registrations = securityProperties.getRegistrations();
        var registration = registrations.get(client.getClientId());
        if (Objects.isNull(registration)) return client;

        var tokenSettings = TokenSettings.builder()
                .accessTokenTimeToLive(Duration.ofSeconds(registration.getTokenAccessTimeToLeave()))
                .refreshTokenTimeToLive(Duration.ofSeconds(registration.getRefreshTokenAccessTimeToLeave()))
                .build();
        client.setClientId(registration.getId());
        client.setTokenSettings(tokenSettings);

        return client;
    }


}
