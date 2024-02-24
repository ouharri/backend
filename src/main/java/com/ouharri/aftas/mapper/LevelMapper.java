package com.ouharri.aftas.mapper;

import com.ouharri.aftas.model.dto.requests.LevelRequest;
import com.ouharri.aftas.model.dto.responses.LevelResponse;
import com.ouharri.aftas.model.entities.Level;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

/**
 * Mapper interface for converting between {@link Level} DTOs and entities.
 * Uses MapStruct for automatic mapping implementation.
 */
@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface LevelMapper extends _Mapper<UUID, LevelRequest, LevelResponse, Level> {

}