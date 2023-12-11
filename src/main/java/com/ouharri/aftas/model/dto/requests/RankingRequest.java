package com.ouharri.aftas.model.dto.requests;

import com.ouharri.aftas.model.entities.Ranking;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for {@link Ranking}
 */
public record RankingRequest(
        @NotNull(message = "Score cannot be null.")
        Long score,
        @NotNull(message = "Rank cannot be null.")
        Integer rank,
        @NotNull
        CompetitionRequest competition
) implements Request {
}