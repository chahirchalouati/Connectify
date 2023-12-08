package com.crcl.core.exceptions;

import jakarta.validation.ConstraintDefinitionException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

/**
 * This class is a global exception handler for common exceptions that occur in the application.
 * It provides methods to handle specific exceptions and return appropriate error responses.
 *
 * In order to use this class as a global exception handler, the class must be annotated with
 * the @ControllerAdvice annotation.
 */
@ControllerAdvice
public class CommonGlobalHandlerException {
    /**
     * This method is a global exception handler for MethodArgumentNotValidException.
     * It receives an exception of type MethodArgumentNotValidException and returns a ResponseEntity.
     * It extracts the field errors from the exception, filters out the errors with null default messages,
     * and maps the field names and their corresponding error messages into a map.
     * It then creates a new instance of ErrorResponse using the map of errors and returns it wrapped in a ResponseEntity
     * with HttpStatus.BAD_REQUEST status code.
     *
     * @param exception an instance of MethodArgumentNotValidException containing the field errors
     * @return a ResponseEntity containing an instance of ErrorResponse with the field errors mapped into a map
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> badRequestResponse(MethodArgumentNotValidException exception) {
        final Map<String, String> errorsMap = exception.getBindingResult().getFieldErrors()
                .stream()
                .filter(field -> nonNull(field.getDefaultMessage()))
                .collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage));
        return new ResponseEntity<>(new ErrorResponse(errorsMap), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles the BindException by extracting the field errors and global errors,
     * and then constructing an ErrorResponse object with the collected errors.
     * Returns a ResponseEntity with the ErrorResponse object as the body and a
     * HTTP status code of 400 (Bad Request).
     *
     * @param ex The BindException instance to be handled.
     * @return A ResponseEntity containing the ErrorResponse object.
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException(BindException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getFieldErrors().stream()
                .filter(field -> field.getDefaultMessage() != null)
                .forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));

        ex.getGlobalErrors().stream()
                .filter(globalError -> globalError.getDefaultMessage() != null)
                .forEach(globalError -> errors.put(globalError.getObjectName(), globalError.getDefaultMessage()));

        ErrorResponse errorResponse = new ErrorResponse(errors);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    /**
     * Handles ConstraintDefinitionException and returns an appropriate error response.
     *
     * @param exception The ConstraintDefinitionException to handle.
     * @return The ResponseEntity containing the error message and HTTP status code.
     */
    @ExceptionHandler({ConstraintDefinitionException.class})
    public ResponseEntity<?> constraintDefinitionException(ConstraintDefinitionException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * This method is a global exception handler for RuntimeExceptions that occur in the application.
     *
     * @param exception The RuntimeException that occurred.
     * @return A ResponseEntity object containing an ErrorResponse object and HTTP status code.
     */
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<?> runtimeException(RuntimeException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
