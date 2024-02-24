package com.ouharri.aftas.mapper;

import com.ouharri.aftas.model.dto.requests.HuntingRequest;
import com.ouharri.aftas.model.dto.responses.HuntingResponse;
import com.ouharri.aftas.model.entities.Hunting;
import org.mapstruct.*;

import java.util.UUID;

/**
 * Mapper interface for converting between {@link Hunting} DTOs and entities.
 * Uses MapStruct for automatic mapping implementation.
 */
@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface HuntingMapper extends _Mapper<UUID, HuntingRequest, HuntingResponse, Hunting> {

    @Override
    @Mapping(target = "huntingCompositeKey.fish", source = "fish")
    @Mapping(target = "huntingCompositeKey.member", source = "member")
    @Mapping(target = "huntingCompositeKey.competition", source = "competition")
    Hunting toEntityFromRequest(HuntingRequest request);

    @Override
    @Mapping(target = "huntingCompositeKey.fish", source = "fish")
    @Mapping(target = "huntingCompositeKey.member", source = "member")
    @Mapping(target = "huntingCompositeKey.competition", source = "competition")
    Hunting toEntityFromResponse(HuntingResponse response);

    @Override
    @Mapping(target = "fish", source = "huntingCompositeKey.fish")
    @Mapping(target = "member", source = "huntingCompositeKey.member")
    @Mapping(target = "competition", source = "huntingCompositeKey.competition")
    HuntingResponse toResponse(Hunting entity);
}
