package com.ouharri.aftas.model.dto.Competition;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ouharri.aftas.model.dto.AbstractResp;
import com.ouharri.aftas.model.dto.Address.AddressDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Date;
import java.sql.Time;

/**
 * DTO for {@link com.ouharri.aftas.model.entities.Competition}
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompetitionResp extends AbstractResp {
    @NotNull(message = "The date cannot be null.")
    private Date date;

    @NotNull(message = "The start time cannot be null.")
    private Time startTime;

    @NotNull(message = "The end time cannot be null.")
    private Time endTime;

    @NotNull(message = "The number of participants cannot be null.")
    @Min(message = "The number of participants must be at least 0.", value = 0)
    private Integer numberOfParticipants;

    @NotBlank(message = "The location cannot be empty.")
    private String location;

    @NotNull(message = "The address cannot be null.")
    private AddressDto address;

    @NotNull(message = "The amount cannot be null.")
    private Double amount;
}