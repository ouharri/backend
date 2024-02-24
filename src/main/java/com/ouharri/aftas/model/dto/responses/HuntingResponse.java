package com.ouharri.aftas.model.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ouharri.aftas.model.entities.Hunting;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

/**
 * DTO for {@link Hunting}
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HuntingResponse extends AbstractResponse<UUID> {
    @NotNull(message = "The number of fish cannot be null.")
    @Positive(message = "The number of fish must be a positive integer.")
    private Integer numberOfFish;

    private FishResponse fish;

    private MemberResponse member;

    private CompetitionResponse competition;
}