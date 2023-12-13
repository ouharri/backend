package com.ouharri.aftas.validations.Annotation;

import com.ouharri.aftas.validations.validator.FutureDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom validation annotation to ensure that the date of the competition is in the future.
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FutureDateValidator.class)
public @interface FutureDate {

    /**
     * The default error message when validation fails.
     *
     * @return The error message.
     */
    String message() default "The date of the competition must be in the future.";

    /**
     * Groups the constraint belongs to.
     *
     * @return An array of groups.
     */
    Class<?>[] groups() default {};

    /**
     * Payload type that can be attached to a given constraint declaration.
     *
     * @return An array of payload classes.
     */
    Class<? extends Payload>[] payload() default {};
}
