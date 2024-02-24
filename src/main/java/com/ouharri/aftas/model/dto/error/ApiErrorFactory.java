package com.ouharri.aftas.model.dto.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents the structure of API error responses.
 * This record includes details such as HTTP status, timestamp, error message,
 * debug message, and a list of sub-errors.
 * It provides a standardized format for sending error responses.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">Ouharri Outman</a>
 */
public record ApiErrorFactory(
        HttpStatus status,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
        LocalDateTime timestamp,
        List<String> message,
        String debugMessage,
        List<ApiSubError> subErrors
) {
    /**
     * Constructor for creating an API error.
     *
     * @param status  The HTTP status of the error.
     * @param message The main error message.
     */
    public ApiErrorFactory(HttpStatus status, List<String> message) {
        this(status, LocalDateTime.now(), message, null, null);
    }

    public ApiErrorFactory(List<String> message, Exception ex, List<ApiSubError> subErrors) {
        this(HttpStatus.BAD_REQUEST, LocalDateTime.now(), message, ex.getLocalizedMessage(), subErrors);
    }

    public ApiErrorFactory(HttpStatus status, List<String> message, Exception ex) {
        this(status, LocalDateTime.now(), message, ex.getLocalizedMessage(), null);
    }

    public ApiErrorFactory(HttpStatus status, List<String> message, String localizedMessage, List<ApiSubError> subErrors) {
        this(status, LocalDateTime.now(), message, localizedMessage, subErrors);
    }
}