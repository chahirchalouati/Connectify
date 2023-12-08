package com.crcl.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The EntityNotFoundException class is an exception that is thrown when an entity is not found.
 * It is a subclass of RuntimeException and is used to indicate that a requested entity could not be found.
 *
 * The class provides several constructors to create an instance of the exception with different parameters:
 * - EntityNotFoundException(): Constructs a new exception with null as its detail message.
 * - EntityNotFoundException(String message): Constructs a new exception with the specified detail message.
 * - EntityNotFoundException(String message, Throwable cause): Constructs a new exception with the specified detail message and cause.
 * - EntityNotFoundException(Throwable cause): Constructs a new exception with the specified cause.
 * - EntityNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace):
 * Constructs a new exception with the specified detail message, cause, suppression enabled or disabled, and writable stack trace enabled or disabled.
 *
 * Example usage:
 * try {
 *     // Attempt to find an entity
 *     Entity entity = findEntity();
 *
 *     if (entity == null) {
 *         throw new EntityNotFoundException("Entity not found");
 *     }
 *
 *     // Process the entity
 *     processEntity(entity);
 *
 * } catch (EntityNotFoundException e) {
 *     // Handle the exception
 *     handleEntityNotFound(e);
 * }
 *
 * @see RuntimeException
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {
    /**
     * The EntityNotFoundException class is an exception that is thrown when an entity is not found.
     * It is a subclass of RuntimeException and is used to indicate that a requested entity could not be found.
     *
     * The class provides several constructors to create an instance of the exception with different parameters:
     * - EntityNotFoundException(): Constructs a new exception with null as its detail message.
     * - EntityNotFoundException(String message): Constructs a new exception with the specified detail message.
     * - EntityNotFoundException(String message, Throwable cause): Constructs a new exception with the specified detail message and cause.
     * - EntityNotFoundException(Throwable cause): Constructs a new exception with the specified cause.
     * - EntityNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace):
     * Constructs a new exception with the specified detail message, cause, suppression enabled or disabled, and writable stack trace enabled or disabled.
     *
     * Example usage:
     * try {
     *     // Attempt to find an entity
     *     Entity entity = findEntity();
     *
     *     if (entity == null) {
     *         throw new EntityNotFoundException("Entity not found");
     *     }
     *
     *     // Process the entity
     *     processEntity(entity);
     *
     * } catch (EntityNotFoundException e) {
     *     // Handle the exception
     *     handleEntityNotFound(e);
     * }
     *
     * @see RuntimeException
     */
    public EntityNotFoundException() {
    }

    /**
     * The EntityNotFoundException class is an exception that is thrown when an entity is not found.
     * It is a subclass of RuntimeException and is used to indicate that a requested entity could not be found.
     * Use this exception to handle situations where an entity is expected to exist, but it does not.
     *
     * Example usage:
     * try {
     *     // Attempt to find an entity
     *     Entity entity = findEntity();
     *
     *     if (entity == null) {
     *         throw new EntityNotFoundException("Entity not found");
     *     }
     *
     *     // Process the entity
     *     processEntity(entity);
     *
     * } catch (EntityNotFoundException e) {
     *     // Handle the exception
     *     handleEntityNotFound(e);
     * }
     */
    public EntityNotFoundException(String message) {
        super(message);
    }

    /**
     * The EntityNotFoundException class is an exception that is thrown when an entity is not found.
     * It is a subclass of RuntimeException and is used to indicate that a requested entity could not be found.
     *
     * The class provides several constructors to create an instance of the exception with different parameters:
     *
     * - EntityNotFoundException(String message, Throwable cause): Constructs a new exception with the specified detail message and cause.
     *
     * Example usage:
     * try {
     *     // Attempt to find an entity
     *     Entity entity = findEntity();
     *
     *     if (entity == null) {
     *         throw new EntityNotFoundException("Entity not found");
     *     }
     *
     *     // Process the entity
     *     processEntity(entity);
     *
     * } catch (EntityNotFoundException e) {
     *     // Handle the exception
     *     handleEntityNotFound(e);
     * }
     *
     * @param message the detail message
     * @param cause the cause (which is saved for later retrieval by the Throwable.getCause() method). (A null value is permitted, and indicates that the cause is nonexistent or unknown
     *.)
     *
     * @see RuntimeException
     */
    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new EntityNotFoundException with the specified cause.
     *
     * @param cause the cause of the exception
     */
    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception that is thrown when an entity is not found.
     *
     * <p>
     * This exception is a subclass of {@link RuntimeException} and is used to indicate that a requested entity could not be found.
     * </p>
     *
     * <p>
     * The class provides several constructors to create an instance of the exception with different parameters:
     * </p>
     * <ul>
     *     <li>{@code EntityNotFoundException()} - Constructs a new exception with null as its detail message.</li>
     *     <li>{@code EntityNotFoundException(String message)} - Constructs a new exception with the specified detail message.</li>
     *     <li>{@code EntityNotFoundException(String message, Throwable cause)} - Constructs a new exception with the specified detail message and cause.</li>
     *     <li>{@code EntityNotFoundException(Throwable cause)} - Constructs a new exception with the specified cause.</li>
     *     <li>{@code EntityNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)} - Constructs a new exception with the specified
     * detail message, cause, suppression enabled or disabled, and writable stack trace enabled or disabled.</li>
     * </ul>
     *
     * <p>
     * Example usage:
     * </p>
     * <pre>{@code
     * try {
     *     // Attempt to find an entity
     *     Entity entity = findEntity();
     *
     *     if (entity == null) {
     *         throw new EntityNotFoundException("Entity not found");
     *     }
     *
     *     // Process the entity
     *     processEntity(entity);
     *
     * } catch (EntityNotFoundException e) {
     *     // Handle the exception
     *     handleEntityNotFound(e);
     * }
     * }</pre>
     *
     * @see RuntimeException
     */
    public EntityNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
