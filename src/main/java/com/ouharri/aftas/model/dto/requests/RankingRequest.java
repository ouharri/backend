package com.ouharri.aftas.model.dto.requests;

import com.ouharri.aftas.model.dto.responses.CompetitionResponse;
import com.ouharri.aftas.model.dto.responses.MemberResponse;
import com.ouharri.aftas.model.entities.Ranking;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for {@link Ranking}
 */
public record RankingRequest(
        @NotNull(message = "The competition cannot be null.")
        CompetitionResponse competition,

        @NotNull(message = "The member cannot be null.")
        MemberResponse member,

        @NotNull(message = "Score cannot be null.")
        Long score,
        @NotNull(message = "Rank cannot be null.")
        Integer rank

) implements _Request {
}