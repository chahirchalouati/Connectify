package com.crcl.core.exceptions;


/**
 * ResourceNotFoundException is a subclass of RuntimeException that is thrown when a resource is not found.
 * <p>
 * This exception may be thrown in situations where a resource, such as a file or database record, is expected but cannot be found.
 *
 * @since 1.0
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
