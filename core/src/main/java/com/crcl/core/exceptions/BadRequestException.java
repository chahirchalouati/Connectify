package com.crcl.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The {@code BadRequestException} class is a custom exception that is thrown when a client sends
 * a bad request to the server. It is a subclass of the {@code RuntimeException}.
 *
 * <p>
 * This exception is typically used to indicate that the client's request is syntactically incorrect,
 * contains invalid parameters, or violates the server's request handling rules.
 * </p>
 *
 * <p>
 * When this exception is thrown, the server will respond with a 400 Bad Request HTTP status code.
 * </p>
 *
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * throw new BadRequestException("Invalid request parameters");
 * }</pre>
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }
}
