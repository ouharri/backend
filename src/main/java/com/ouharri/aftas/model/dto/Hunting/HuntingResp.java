package com.ouharri.aftas.model.dto.Hunting;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ouharri.aftas.model.dto.Competition.CompetitionResp;
import com.ouharri.aftas.model.dto.Fish.FishResp;
import com.ouharri.aftas.model.dto.Member.MemberResp;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link com.ouharri.aftas.model.entities.Hunting}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record HuntingResp(UUID id, String createdAt, LocalDateTime updatedAt,
                          @NotNull(message = "The number of fish cannot be null.") @Positive(message = "The number of fish must be a positive integer.") Integer numberOfFish,
                          FishResp fish, MemberResp member, CompetitionResp competition) implements Serializable {
}