package com.crcl.core.exceptions;

/**
 * Exception thrown when an object or entity already exists.
 */
public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }

    public AlreadyExistsException() {
    }
}
