package com.ouharri.aftas.model.mapper;

import com.ouharri.aftas.model.dto.requests.MemberRequest;
import com.ouharri.aftas.model.dto.responces.MemberResponse;
import com.ouharri.aftas.model.entities.Member;

import java.util.UUID;

/**
 * Mapper interface for converting between {@link Member} DTOs and entities.
 * Uses MapStruct for automatic mapping implementation.
 */
public interface MemberMapper extends _Mapper<UUID, MemberRequest, MemberResponse, Member> {
}
