package com.ouharri.aftas.model.dto.requests;

import com.ouharri.aftas.model.entities.Competition;
import com.ouharri.aftas.validations.Annotation.EndTimeAfterStartTime;
import com.ouharri.aftas.validations.Annotation.FutureDate;
import com.ouharri.aftas.validations.interfaces.DateTimeValidator;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Time;

/**
 * DTO for {@link Competition}
 */

@EndTimeAfterStartTime
public record CompetitionRequest(
        @NotBlank(message = "The title cannot be empty.")
        String title,

        @Getter
        @FutureDate
        @NotNull(message = "The date cannot be null.")
        Date date,

        @Getter
        @NotNull(message = "The start time cannot be null.")
        @DateTimeFormat(pattern = "HH:mm:ss")
        Time startTime,

        @Getter
        @NotNull(message = "The end time cannot be null.")
        @DateTimeFormat(pattern = "HH:mm:ss")
        Time endTime,

        @NotNull(message = "The number of participants cannot be null.")
        @Min(message = "The number of participants must be at least 0.", value = 0)
        Integer numberOfParticipants,

        String location,

        @NotNull(message = "The address cannot be null.")
        AddressRequest address,

        @Min(0)
        @NotNull(message = "The amount cannot be null.")
        Double amount
) implements _Request, DateTimeValidator {
}