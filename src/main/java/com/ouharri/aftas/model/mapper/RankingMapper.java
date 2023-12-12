package com.ouharri.aftas.model.mapper;

import com.ouharri.aftas.model.dto.requests.RankingRequest;
import com.ouharri.aftas.model.dto.responces.RankingResponse;
import com.ouharri.aftas.model.entities.Ranking;
import com.ouharri.aftas.model.entities.RankingId;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

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

}
