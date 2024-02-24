package com.ouharri.aftas.model.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Base class for representing sub-errors in API error responses.
 * This class can be extended to define specific types of sub-errors.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">Ouharri Outman</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiSubError {
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
    public ApiSubError(String object, String message) {
        this.object = object;
        this.message = message;
    }
}
