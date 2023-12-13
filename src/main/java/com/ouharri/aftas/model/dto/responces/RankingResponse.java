package com.ouharri.aftas.model.dto.responces;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

/**
 * DTO for {@link com.ouharri.aftas.model.entities.Ranking}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record RankingResponse(
        CompetitionResponse competition,
        MemberResponse member,

        @NotNull(message = "Score cannot be null.")
        Long score,
        @NotNull(message = "Rank cannot be null.")
        Integer rank,
        Timestamp createdAt,
        Timestamp updatedAt
) implements _Response {
}