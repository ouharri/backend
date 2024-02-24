package com.ouharri.aftas.mapper;

import com.ouharri.aftas.model.dto.requests.FishRequest;
import com.ouharri.aftas.model.dto.responses.FishResponse;
import com.ouharri.aftas.model.entities.Fish;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

/**
 * Mapper interface for converting between {@link Fish} DTOs and entities.
 * Uses MapStruct for automatic mapping implementation.
 */
@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface FishMapper extends _Mapper<UUID, FishRequest, FishResponse, Fish> {

}