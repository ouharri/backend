package com.ouharri.aftas.exceptions;

import com.ouharri.aftas.model.dto.error.ApiErrorFactory;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * Custom exception class for handling cases where a user is not authenticated.
 * This class extends {@link ResourceException} to provide additional functionality
 * for unauthorized access scenarios.
 * <p>
 * It uses {@link ResponseStatus} to automatically send an HTTP 401 Unauthorized status
 * when this exception is thrown. An {@link ExceptionHandler} is also provided to customize
 * the response body sent when this exception is handled.
 * </p>
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 */
@Getter
@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class NoAuthenticateUser extends ResourceException {

    private static final String DEFAULT_MESSAGE = "User not authenticated.";

    /**
     * Constructs a new NoAuthenticateUser exception with a custom message.
     *
     * @param message Custom message for the exception.
     */
    public NoAuthenticateUser(String message) {
        super(message);
    }

    /**
     * Constructs a new NoAuthenticateUser exception with the default message.
     */
    public NoAuthenticateUser() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Exception handler for NoAuthenticateUser exceptions.
     * This method wraps the exception message in a {@link ApiErrorFactory} object
     * and returns it with an HTTP 401 Unauthorized status.
     *
     * @param ex The NoAuthenticateUser exception.
     * @return ResponseEntity containing the ApiErrorFactory and HTTP status.
     */
    @ExceptionHandler(NoAuthenticateUser.class)
    public ResponseEntity<ApiErrorFactory> handleInvalidPaymentException(NoAuthenticateUser ex) {
        ApiErrorFactory apiError = new ApiErrorFactory(
                HttpStatus.UNAUTHORIZED,
                List.of(ex.getMessage())
        );
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }
}