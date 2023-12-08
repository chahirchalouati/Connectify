package com.crcl.core.dto.queue.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * {@code QEvent} is an abstract base class for events in a queue-based system.
 * It provides methods for setting the payload and headers associated with the event.
 * Subclasses must provide implementations for the abstract methods.
 *
 * @param <T> the type of the payload
 * @param <E> the type of the event subclass
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class QEvent<T, E extends QEvent<T, E>> {

    protected T payload;
    protected Map<String, Object> headers;

    /**
     * Initializes a new instance of the QEvent class with the specified payload.
     *
     * @param payload the payload associated with the event
     */
    public QEvent(T payload) {
        this.payload = payload;
    }

    /**
     * Sets the payload of the event.
     *
     * @param payload the payload to set
     * @return the modified event instance
     * @throws IllegalArgumentException if the payload is null
     */
    public E setPayload(T payload) {
        Assert.notNull(payload, "The given payload can't be null");
        this.payload = payload;
        return self();
    }

    /**
     * Sets the headers for the event.
     *
     * @param headers a map of headers to be set
     * @return the event instance with the headers set
     */
    public E setHeaders(Map<String, Object> headers) {
        this.headers = headers;
        return self();
    }

    /**
     * Sets the payload for the event.
     *
     * @param payload the payload for the event
     * @return the event instance with the specified payload
     */
    public abstract E withPayload(T payload);

    /**
     * Sets the headers for the event.
     *
     * @param headers the headers to set for the event
     * @return the event object with the updated headers
     */
    public abstract E withHeaders(Map<String, Object> headers);

    /**
     * Returns the current instance of the event subclass.
     * This method is used for method chaining in the event subclasses.
     *
     * @return the current instance of the event subclass
     */
    protected abstract E self();
}