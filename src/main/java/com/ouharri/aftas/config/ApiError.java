/**
 * This package contains classes for handling and representing API error responses.
 * The classes provide a structured approach to encapsulating different types of errors,
 * including constraint-related and validation-related errors.
 * The primary class, {@link com.ouharri.aftas.config.ApiError}, represents the overall structure
 * of API error responses and includes information such as HTTP status, timestamp, main error message,
 * debug message, and a list of sub-errors.
 *
 * <p>
 * The classes in this package are designed to be flexible, with the use of Lombok annotations
 * to reduce boilerplate code and a record type for simplifying the creation of immutable data classes.
 * The error classes support detailed error reporting with sub-errors for more granular information.
 *
 * <p>
 */
package com.ouharri.aftas.config;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Abstract class representing sub-errors in API error responses.
 * This class serves as the base class for specific sub-error types.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">ouharri outman</a>
 */
abstract class ApiSubError {
}

/**
 * Represents a constraint-related error in API error responses.
 * Extends the {@link ApiSubError} abstract class.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">ouharri outman</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
class ApiConstraintError extends ApiSubError {
    private String field;
    private String message;
}

/**
 * Represents a validation-related error in API error responses.
 * Extends the {@link ApiSubError} abstract class.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">ouharri outman</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
class ApiValidationError extends ApiSubError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    /**
     * Constructor for creating a validation error.
     *
     * @param object  The object name.
     * @param message The error message.
     */
    ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }
}

/**
 * Represents the structure of API error responses as a record.
 * It includes information such as HTTP status, timestamp, error message,
 * debug message, and a list of sub-errors.
 *
 * @param status       The HTTP status of the error.
 * @param timestamp    The timestamp when the error occurred.
 * @param message      The main error message.
 * @param debugMessage The debug message providing additional information about the error.
 * @param subErrors    A list of sub-errors that provide more details about the main error.
 */
record ApiError(
        HttpStatus status,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
        LocalDateTime timestamp,
        String message,
        String debugMessage,
        List<ApiSubError> subErrors
) {
    /**
     * Constructor for creating an API error with specific HTTP status, message, and exception.
     *
     * @param status    The HTTP status of the error.
     * @param message   The main error message.
     * @param ex        The exception that caused the error.
     * @param subErrors A list of sub-errors that provide more details about the main error.
     */

    public ApiError(HttpStatus status, String message, Throwable ex, List<ApiSubError> subErrors) {
        this(status, LocalDateTime.now(), message, ex.getLocalizedMessage(), subErrors);
    }

    /**
     * Constructor for creating an API error with specific HTTP status, message, and exception.
     *
     * @param status  The HTTP status of the error.
     * @param message The main error message.
     * @param ex      The exception
     */
    public ApiError(HttpStatus status, String message, Throwable ex) {
        this(status, LocalDateTime.now(), message, ex.getLocalizedMessage(), null);
    }
}