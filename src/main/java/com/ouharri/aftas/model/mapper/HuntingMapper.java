package com.ouharri.aftas.model.mapper;

import com.ouharri.aftas.model.dto.requests.HuntingRequest;
import com.ouharri.aftas.model.dto.responces.HuntingResponse;
import com.ouharri.aftas.model.entities.Hunting;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

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
}
