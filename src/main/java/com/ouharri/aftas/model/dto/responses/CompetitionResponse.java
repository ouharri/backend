package com.ouharri.aftas.model.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ouharri.aftas.model.dto.requests.AddressRequest;
import com.ouharri.aftas.model.entities.Competition;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link Competition}
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompetitionResponse extends AbstractResponse<UUID> {
    @NotBlank(message = "The title cannot be empty.")
    private String title;

    @Pattern(regexp = "^[a-zA-Z]{3}-\\d{2}-\\d{2}-\\d{2}$", message = "The code must follow the specified format.")
    private String code;

    @NotNull(message = "The date cannot be null.")
    private Date date;

    @NotNull(message = "The start time cannot be null.")
    private Time startTime;

    @NotNull(message = "The end time cannot be null.")
    private Time endTime;

    @NotNull(message = "The number of participants cannot be null.")
    @Min(message = "The number of participants must be at least 0.", value = 0)
    private Integer numberOfParticipants;

    private String location;

    @NotNull(message = "The address cannot be null.")
    private AddressRequest address;

    @NotNull(message = "The amount cannot be null.")
    private Double amount;

    private List<MemberResponse> members = new ArrayList<>();
}