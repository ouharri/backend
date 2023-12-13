package com.ouharri.aftas.validations.Annotation;

import com.ouharri.aftas.validations.validator.EndTimeAfterStartTimeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom validation annotation to ensure that the end time is after the start time.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EndTimeAfterStartTimeValidator.class)
public @interface EndTimeAfterStartTime {
    /**
     * The default error message when validation fails.
     *
     * @return The error message.
     */
    String message() default "The end time must be after the start time.";

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