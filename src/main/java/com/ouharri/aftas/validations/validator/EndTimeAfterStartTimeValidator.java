package com.ouharri.aftas.validations.validator;

import com.ouharri.aftas.validations.Annotation.EndTimeAfterStartTime;
import com.ouharri.aftas.validations.interfaces.DateTimeValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.sql.Date;

/**
 * Custom validator for ensuring that the end time of a competition is after the start time.
 */
public class EndTimeAfterStartTimeValidator implements ConstraintValidator<EndTimeAfterStartTime, DateTimeValidator> {

    /**
     * Validates that the end time is after the start time for a given competition.
     *
     * @param competition The competition to be validated.
     * @param context     The validation context.
     * @return true if the end time is after the start time, false otherwise.
     */
    @Override
    public boolean isValid(DateTimeValidator competition, ConstraintValidatorContext context) {
        if (competition == null) {
            return true;
        }

        Date date = competition.getDate();
        Date currentDate = new Date(System.currentTimeMillis());

        return (date.toLocalDate().isEqual(currentDate.toLocalDate())
                || date.toLocalDate().isAfter(currentDate.toLocalDate())) &&
                competition.getStartTime() != null &&
                competition.getEndTime() != null &&
                competition.getEndTime().after(competition.getStartTime());
    }
}
