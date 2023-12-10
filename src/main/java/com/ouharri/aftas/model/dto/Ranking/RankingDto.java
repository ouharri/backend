package com.ouharri.aftas.model.dto.Ranking;

import com.ouharri.aftas.model.dto.Competition.CompetitionDto;
import com.ouharri.aftas.model.entities.Ranking;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link Ranking}
 */
public record RankingDto(
        @NotNull(message = "Score cannot be null.") Long score,
        @NotNull(message = "Rank cannot be null.") Integer rank,
        @NotNull CompetitionDto competition) implements Serializable {
}