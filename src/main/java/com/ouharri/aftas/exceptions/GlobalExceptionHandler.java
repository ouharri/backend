package com.ouharri.aftas.exceptions;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Global exception handler for handling various exceptions in the API.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">ouharri outman</a>
 */
@Slf4j
@ControllerAdvice
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    /**
     * Handle HttpMessageNotReadableException and return a proper API error response.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ApiErrorFactory> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpStatus status) {
        ApiErrorFactory apiError = new ApiErrorFactory(status,
                List.of("Malformed JSON request"), ex);
        log.error("Handling HttpMessageNotReadableException", ex);
        return buildResponseEntity(apiError);
    }


    /**
     * Handle EntityNotFoundException and return a proper API error response.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ApiErrorFactory> handleEntityNotFound(EntityNotFoundException ex) {
        ApiErrorFactory apiError = new ApiErrorFactory(
                HttpStatus.NOT_FOUND,
                List.of(ex.getMessage()),
                ex
        );
        log.error("Handling EntityNotFoundException", ex);
        return buildResponseEntity(apiError);
    }

    /**
     * Handle MethodArgumentNotValidException and return a map of validation errors.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ApiSubError> subErrors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            if (error instanceof FieldError fieldError) {
                subErrors.add(new ApiValidationError(fieldError.getObjectName(), fieldError.getField(),
                        fieldError.getRejectedValue(), fieldError.getDefaultMessage()));
            } else {
                subErrors.add(new ApiValidationError(error.getObjectName(), error.getDefaultMessage()));
            }
        });

        ApiErrorFactory apiError = new ApiErrorFactory(
                HttpStatus.BAD_REQUEST,
                ex.getBindingResult().getAllErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList()),
                ex, subErrors
        );

        log.error("Handling MethodArgumentNotValidException", ex);
        return ResponseEntity.badRequest().body(apiError);
    }

    /**
     * Handle generic exceptions and return a proper API error response.
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorFactory> handleExceptions(Exception ex) {
        ApiErrorFactory apiError = new ApiErrorFactory(
                HttpStatus.INTERNAL_SERVER_ERROR,
                List.of(ex.getLocalizedMessage()),
                ex
        );
        log.error("Handling generic exception", ex);
        return buildResponseEntity(apiError);
    }


    @ExceptionHandler({MalformedJwtException.class, SignatureException.class, JwtException.class, JpaSystemException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ApiErrorFactory> handleMalformedJwtException(Exception ex) {
        ApiErrorFactory apiError = new ApiErrorFactory(
                HttpStatus.BAD_REQUEST,
                List.of(ex.getLocalizedMessage())
        );
        log.error("Handling JWT-related exception", ex);
        return buildResponseEntity(apiError);
    }


    /**
     * Handle ConstraintViolationException and return a proper API error response.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorFactory> handleConstraintViolationException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        List<ApiSubError> subErrors = new ArrayList<>();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            subErrors.add(new ApiValidationError(constraintViolation.getRootBeanClass().getSimpleName(),
                    constraintViolation.getPropertyPath().toString(), constraintViolation.getInvalidValue(),
                    constraintViolation.getMessage()));
        }
        ApiErrorFactory apiError = new ApiErrorFactory(
                HttpStatus.BAD_REQUEST,
                ex.getConstraintViolations().stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.toList()),
                ex, subErrors
        );

        log.error("Handling ConstraintViolationException", ex);
        return buildResponseEntity(apiError);
    }


    private ResponseEntity<ApiErrorFactory> buildResponseEntity(ApiErrorFactory apiError) {
        return new ResponseEntity<>(apiError, apiError.status());
    }
}