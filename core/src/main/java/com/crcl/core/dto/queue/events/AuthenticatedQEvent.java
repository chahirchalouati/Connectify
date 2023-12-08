package com.crcl.core.dto.queue.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.Assert;

import java.util.Map;


/**
 * The AuthenticatedQEvent class represents an authenticated event in a queue-based system.
 * It extends the QEvent class and provides additional functionality for token and security context management.
 *
 * @param <T> the type of the payload
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AuthenticatedQEvent<T> extends QEvent<T, AuthenticatedQEvent<T>> {

    private String token;
    private Object securityContext;

    /**
     * Set the token for the authenticated event.
     *
     * @param token the token to be set for the event
     * @return the modified event instance
     * @throws IllegalArgumentException if the token is null
     */
    public AuthenticatedQEvent<T> withToken(String token) {
        Assert.notNull(token, "The given token can't be null");
        this.token = token;
        return self();
    }

    /**
     * Set the security context for the authenticated QEvent.
     *
     * @param securityContext the security context to set
     * @return the modified AuthenticatedQEvent instance with the security context set
     * @throws IllegalArgumentException if the security context is null
     * @deprecated This method is deprecated and will be removed in version 1.0.2
     * @since 1.0.2
     */
    @Deprecated(forRemoval = true, since = "1.0.2")
    public AuthenticatedQEvent<T> withSecurityContext(Object securityContext) {
        Assert.notNull(securityContext, "The given securityContext can't be null");
        this.securityContext = securityContext;
        return self();
    }

    /**
     * Returns a new instance of AuthenticatedQEvent with the specified payload.
     *
     * @param payload the payload to set
     * @return a new instance of AuthenticatedQEvent with the specified payload
     * @throws IllegalArgumentException if the payload is null
     */
    @Override
    public AuthenticatedQEvent<T> withPayload(T payload) {
        return self().setPayload(payload);
    }

    /**
     * Returns a new instance of AuthenticatedQEvent with the specified headers.
     *
     * @param headers a map of headers to be set
     * @return a new instance of AuthenticatedQEvent with the headers set
     */
    @Override
    public AuthenticatedQEvent<T> withHeaders(Map<String, Object> headers) {
        return self().setHeaders(headers);
    }

    /**
     * Returns a new instance of DefaultQEvent with the same payload and headers as this AuthenticatedQEvent.
     *
     * @return a new instance of DefaultQEvent with the same payload and headers
     */
    public DefaultQEvent<T> asDefaultQEvent() {
        return new DefaultQEvent<T>().withPayload(this.payload).withHeaders(this.headers);
    }

    /**
     * Returns the current instance of the AuthenticatedQEvent subclass.
     * This method is used for method chaining in the AuthenticatedQEvent subclasses.
     *
     * @return the current instance of the AuthenticatedQEvent subclass
     */
    @Override
    protected AuthenticatedQEvent<T> self() {
        return this;
    }
}
