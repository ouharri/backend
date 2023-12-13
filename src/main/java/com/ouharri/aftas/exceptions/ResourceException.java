package com.ouharri.aftas.exceptions;

import com.ouharri.aftas.model.entities.User;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * The parent for all exceptions, associated with resources, such as {@link User} etc.
 *
 * @author Ouharri Outman
 * @version 1.0
 */
@RestControllerAdvice
public class ResourceException extends RuntimeException {
    public ResourceException() {
    }

    public ResourceException(String message) {
        super(message);
    }
}
