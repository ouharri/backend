package com.ouharri.aftas.model.dto.responces;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.ouharri.aftas.model.entities.Ranking}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record RankingResponse(
        RankingIdResponse id,
        @NotNull(message = "Score cannot be null.")
        Long score,
        @NotNull(message = "Rank cannot be null.")
        Integer rank,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) implements Response {
}