package com.crcl.core.exceptions;

import jakarta.validation.ConstraintDefinitionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static java.util.Objects.nonNull;

/**
 * The CommonGlobalHandlerException class is a controller advice class that handles global exceptions
 * and provides consistent error responses for different types of exceptions.
 *
 * @ControllerAdvice annotation marks this class as an advice component that will be applied globally to all
 * controllers in the application.
 */
@ControllerAdvice
public class CommonGlobalHandlerException {

    /**
     * Generates a bad request response with a ProblemDetail object containing information about the validation failure.
     *
     * @param exception The MethodArgumentNotValidException that occurred during validation.
     * @return A ResponseEntity object containing a ProblemDetail object and HttpStatus.BAD_REQUEST.
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> badRequestResponse(MethodArgumentNotValidException exception) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Validation failed");

        exception.getBindingResult().getFieldErrors()
                .stream()
                .filter(field -> nonNull(field.getDefaultMessage()))
                .forEach(fieldError -> problemDetail.setProperty(fieldError.getField(), fieldError.getDefaultMessage()));

        return new ResponseEntity<>(problemDetail, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles a BindException and returns a ResponseEntity with a ProblemDetail object.
     *
     * @param exception The BindException that occurred.
     * @return A ResponseEntity with a ProblemDetail object containing information about the exception.
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleBindException(BindException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST.value());
        problemDetail.setTitle("Bind Exception occurred");
        exception.getFieldErrors()
                .stream()
                .filter(field -> nonNull(field.getDefaultMessage()))
                .forEach(fieldError -> problemDetail.setProperty(fieldError.getField(), fieldError.getDefaultMessage()));

        return ResponseEntity.badRequest().body(problemDetail);
    }

    /**
     * Handles ConstraintDefinitionException and returns a ResponseEntity with a ProblemDetail object containing the error details.
     *
     * @param exception The ConstraintDefinitionException to be handled.
     * @return A ResponseEntity containing a ProblemDetail object and the HTTP status code.
     */
    @ExceptionHandler({ConstraintDefinitionException.class})
    public ResponseEntity<?> constraintDefinitionException(ConstraintDefinitionException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST.value());
        problemDetail.setTitle("Constraint Definition Exception occurred");
        problemDetail.setDetail(exception.getMessage());

        return new ResponseEntity<>(problemDetail, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles the runtime exceptions and generates a consistent error response.
     *
     * @param exception The runtime exception that occurred.
     * @return The response entity containing the problem detail with the error information.
     */
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<?> runtimeException(RuntimeException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        problemDetail.setTitle("Runtime Exception occurred");
        problemDetail.setDetail(exception.getMessage());

        return new ResponseEntity<>(problemDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}