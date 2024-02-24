package com.ouharri.aftas.mapper;

import com.ouharri.aftas.model.dto.requests.CompetitionRequest;
import com.ouharri.aftas.model.dto.responses.CompetitionResponse;
import com.ouharri.aftas.model.entities.Competition;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

/**
 * Mapper interface for converting between {@link Competition} DTOs and entities.
 * Uses MapStruct for automatic mapping implementation.
 */
@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface CompetitionMapper extends _Mapper<UUID, CompetitionRequest, CompetitionResponse, Competition> {

}