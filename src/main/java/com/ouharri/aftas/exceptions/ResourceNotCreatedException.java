package com.ouharri.aftas.exceptions;

import com.ouharri.aftas.model.dto.error.ApiErrorFactory;
import lombok.Getter;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

/**
 * Exception class for situations where an attempt to create an entity fails.
 * It can be caused by validation violations or uniqueness constraints.
 *
 * @author Ouharri Outman
 * @version 1.0
 */
@Getter
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ResourceNotCreatedException extends ResourceException {
    private BindingResult bindingResult;
    private Map<String, String> matches;

    /**
     * Constructor for ResourceNotCreatedException with a custom message.
     *
     * @param message The custom error message.
     */
    public ResourceNotCreatedException(String message) {
        super(message);
    }

    /**
     * Constructor for ResourceNotCreatedException caused by validation violations.
     *
     * @param bindingResult Hibernate Validation object that wraps validation violations.
     */
    public ResourceNotCreatedException(BindingResult bindingResult) {
        super();
        this.bindingResult = bindingResult;
    }

    /**
     * Constructor for ResourceNotCreatedException caused by uniqueness violation.
     *
     * @param matches Map of fields that violate uniqueness constraint.
     */
    public ResourceNotCreatedException(Map<String, String> matches) {
        super();
        this.matches = matches;
    }

    /**
     * Exception handler method for handling ResourceNotCreatedException.
     * Converts the exception into an ApiErrorFactory and returns it in a ResponseEntity.
     *
     * @param ex The ResourceNotCreatedException to handle.
     * @return ResponseEntity with ApiErrorFactory representing the error.
     */
    @ExceptionHandler(ResourceNotCreatedException.class)
    public ResponseEntity<ApiErrorFactory> handleValidationException(ResourceNotCreatedException ex) {
        ApiErrorFactory apiError = new ApiErrorFactory(
                HttpStatus.BAD_REQUEST,
                ex.getBindingResult().getAllErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .toList()
        );
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}