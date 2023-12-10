package com.ouharri.aftas.model.dto.Ranking;

import com.ouharri.aftas.model.dto.Competition.CompetitionReq;
import com.ouharri.aftas.model.dto.Member.MemberReq;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.ouharri.aftas.model.entities.RankingId}
 */
public record RankingIdReq(
        @NotNull(message = "The competition cannot be null.") CompetitionReq competition,
        @NotNull MemberReq member
) implements Serializable {
}