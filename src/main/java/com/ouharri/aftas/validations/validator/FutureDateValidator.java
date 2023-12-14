package com.ouharri.aftas.validations.validator;

import com.ouharri.aftas.validations.Annotation.FutureDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.sql.Date;

/**
 * Validator for the {@link FutureDate} constraint.
 * Ensures that the provided date is in the future.
 */
public class FutureDateValidator implements ConstraintValidator<FutureDate, Date> {

    /**
     * Validates whether the provided date is in the future.
     *
     * @param date    The date to be validated.
     * @param context The validation context.
     * @return {@code true} if the date is in the future or {@code null}, {@code false} otherwise.
     */
    @Override
    public boolean isValid(Date date, ConstraintValidatorContext context) {
        if (date == null) {
            return true;
        }

        Date currentDate = new Date(System.currentTimeMillis());

        return date.toLocalDate().isEqual(currentDate.toLocalDate())
                || date.toLocalDate().isAfter(currentDate.toLocalDate());
    }
}
