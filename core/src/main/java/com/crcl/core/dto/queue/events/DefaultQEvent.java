package com.crcl.core.dto.queue.events;

import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * DefaultQEvent is a concrete subclass of QEvent that provides a default implementation for the abstract methods.
 * It is used for events in a queue-based system and can be customized by providing a payload and headers.
 *
 * @param <T> the type of the payload
 */
@NoArgsConstructor
public class DefaultQEvent<T> extends QEvent<T, DefaultQEvent<T>> {
    /**
     * Constructs a new instance of DefaultQEvent with the specified payload.
     *
     * @param payload the payload associated with the event
     */
    public DefaultQEvent(T payload) {
        super(payload);
    }

    /**
     * Returns a new instance of DefaultQEvent with the specified payload.
     *
     * @param payload the payload to set
     * @return a new instance of DefaultQEvent with the specified payload
     * @throws IllegalArgumentException if the payload is null
     */
    @Override
    public DefaultQEvent<T> withPayload(T payload) {
        return self().setPayload(payload);
    }

    /**
     * Returns a new instance of DefaultQEvent with the specified headers.
     *
     * @param headers a map of headers to be set
     * @return a new instance of DefaultQEvent with the headers set
     */
    @Override
    public DefaultQEvent<T> withHeaders(Map<String, Object> headers) {
        return self().setHeaders(headers);
    }

    /**
     * Returns the current instance of the DefaultQEvent subclass.
     * This method is used for method chaining in the DefaultQEvent subclasses.
     *
     * @return the current instance of the DefaultQEvent subclass
     */
    @Override
    protected DefaultQEvent<T> self() {
        return this;
    }
}
