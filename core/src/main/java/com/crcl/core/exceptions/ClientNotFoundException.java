package com.crcl.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The ClientNotFoundException class is a custom exception that is thrown when a client is not found.
 * It extends the RuntimeException class and is annotated with @ResponseStatus(HttpStatus.UNAUTHORIZED) to indicate the HTTP response status code to be returned when this exception
 * is thrown.
 *
 * This class provides several constructors to accommodate different use cases:
 * - ClientNotFoundException(): Constructs a new ClientNotFoundException with no specified detail message.
 * - ClientNotFoundException(String message): Constructs a new ClientNotFoundException with the specified detail message.
 * - ClientNotFoundException(String message, Throwable cause): Constructs a new ClientNotFoundException with the specified detail message and cause.
 * - ClientNotFoundException(Throwable cause): Constructs a new ClientNotFoundException with the specified cause.
 * - ClientNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace): Constructs a new ClientNotFoundException with the specified detail
 * message, cause, suppression enabled or disabled, and writable stack trace enabled or disabled.
 *
 * Example usage:
 *
 * try {
 *     // Code that may throw a ClientNotFoundException
 * } catch (ClientNotFoundException e) {
 *     // Code to handle the exception
 * }
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException() {
        super();
    }

    public ClientNotFoundException(String message) {
        super(message);
    }

    public ClientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ClientNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
