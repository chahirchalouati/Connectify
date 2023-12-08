package com.crcl.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class represents an exception that is thrown when an internal server error occurs.
 * It is a sub-class of the RuntimeException class.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends RuntimeException {
    /**
     * This class represents an exception that is thrown when an internal server error occurs.
     * It is a sub-class of the RuntimeException class.
     */
    public InternalServerErrorException() {
    }

    /**
     * Creates an InternalServerErrorException with the specified error message.
     *
     * @param message the error message describing the internal server error
     */
    public InternalServerErrorException(String message) {
        super(message);
    }
}
