package com.ouharri.aftas.mapper;

import com.ouharri.aftas.model.dto.requests.MemberRequest;
import com.ouharri.aftas.model.dto.responses.MemberResponse;
import com.ouharri.aftas.model.entities.Member;
import org.mapstruct.*;

import java.util.UUID;

/**
 * Mapper interface for converting between {@link Member} DTOs and entities.
 * Uses MapStruct for automatic mapping implementation.
 */
@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface MemberMapper extends _Mapper<UUID, MemberRequest, MemberResponse, Member> {
    @Override
    @Mapping(target = "id", ignore = true)
    Member toEntityFromRequest(MemberRequest request);
}
