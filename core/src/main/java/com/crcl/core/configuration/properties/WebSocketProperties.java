package com.crcl.core.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * The WebSocketProperties class represents the properties for configuring a WebSocket connection.
 *
 * <p>It is annotated with {@code @Data} and {@code @Configuration} to enable data encapsulation
 * and indicate that it is a Spring configuration class. The class is also annotated with
 * {@code @ConfigurationProperties} to specify the prefix for the properties in the application
 * configuration file.</p>
 *
 * <p>The properties in this class include the end point, prefixes, relay host, STOMP broker relay,
 * relay port, client login, and client passcode.</p>
 *
 * @see Data
 * @see Configuration
 * @see ConfigurationProperties
 */
@Data
@Configuration
@ConfigurationProperties("web-socket")
public class WebSocketProperties {
    private String endPoint;
    private String[] prefixes;
    private String relayHost;
    private String stompBrokerRelay;
    private int relayPort;
    private String clientLogin;
    private String clientPasscode;
}
