package com.ouharri.aftas.model.dto.Ranking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ouharri.aftas.model.dto.Competition.CompetitionResp;
import com.ouharri.aftas.model.dto.Member.MemberResp;

import java.io.Serializable;

/**
 * DTO for {@link com.ouharri.aftas.model.entities.RankingId}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record RankingIdResp(CompetitionResp competition, MemberResp member) implements Serializable {
}