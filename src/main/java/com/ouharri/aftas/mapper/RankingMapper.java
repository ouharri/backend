package com.ouharri.aftas.mapper;

import com.ouharri.aftas.model.dto.requests.RankingRequest;
import com.ouharri.aftas.model.dto.responses.RankingResponse;
import com.ouharri.aftas.model.entities.Ranking;
import com.ouharri.aftas.model.entities.RankingId;
import org.mapstruct.*;

/**
 * Mapper interface for converting between {@link Ranking} DTOs and entities.
 * Uses MapStruct for automatic mapping implementation.
 */
@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface RankingMapper extends _Mapper<RankingId, RankingRequest, RankingResponse, Ranking> {
    @Override
    @Mapping(target = "id.member", source = "member")
    @Mapping(target = "id.competition", source = "competition")
    Ranking toEntityFromRequest(RankingRequest request);

    @Override
    @Mapping(target = "id.member", source = "member")
    @Mapping(target = "id.competition", source = "competition")
    Ranking toEntityFromResponse(RankingResponse request);

    @Override
    @Mapping(target = "member", source = "id.member")
    @Mapping(target = "competition", source = "id.competition")
    RankingResponse toResponse(Ranking entity);
}