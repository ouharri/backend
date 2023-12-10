package com.ouharri.aftas.model.dto.Ranking;

import com.ouharri.aftas.model.dto.Competition.CompetitionDto;
import com.ouharri.aftas.model.dto.Member.MemberDto;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.ouharri.aftas.model.entities.RankingId}
 */
public record RankingIdDto(
        @NotNull(message = "The competition cannot be null.") CompetitionDto competition,
        @NotNull MemberDto member
) implements Serializable {
}