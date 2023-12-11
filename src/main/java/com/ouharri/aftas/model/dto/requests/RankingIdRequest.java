package com.ouharri.aftas.model.dto.requests;

import jakarta.validation.constraints.NotNull;

/**
 * DTO for {@link com.ouharri.aftas.model.entities.RankingId}
 */
public record RankingIdRequest(
        @NotNull(message = "The competition cannot be null.")
        CompetitionRequest competition,

        @NotNull
        MemberRequest member
) implements Request {
}