package com.ouharri.aftas.model.dto.responces;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO for {@link com.ouharri.aftas.model.entities.RankingId}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record RankingIdResponse(
        CompetitionResponse competition,
        MemberResponse member
) implements Response {
}