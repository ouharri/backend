package com.ouharri.aftas.validations.interfaces;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.sql.Time;

/**
 * Interface for validating date and time fields.
 */
public interface DateTimeValidator {

    /**
     * Gets the date value to be validated.
     *
     * @return The date value.
     */
    @NotNull(message = "The date cannot be null.")
    @Temporal(TemporalType.DATE)
    Date getDate();

    /**
     * Gets the start time value to be validated.
     *
     * @return The start time value.
     */
    @NotNull(message = "The start time cannot be null.")
    @Temporal(TemporalType.TIME)
    Time getStartTime();

    /**
     * Gets the end time value to be validated.
     *
     * @return The end time value.
     */
    @NotNull(message = "The end time cannot be null.")
    @Temporal(TemporalType.TIME)
    Time getEndTime();
}
