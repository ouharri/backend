package com.ouharri.aftas.model.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ouharri.aftas.model.entities.Ranking;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * DTO for {@link Ranking}
 */
@Getter
@Setter
@SuperBuilder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RankingResponse extends AuditableResponse implements _Response {

    CompetitionResponse competition;

    MemberResponse member;

    @NotNull(message = "Score cannot be null.")
    Long score;

    @NotNull(message = "Rank cannot be null.")
    Integer rank;
}