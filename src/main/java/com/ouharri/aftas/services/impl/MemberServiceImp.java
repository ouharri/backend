package com.ouharri.aftas.services.impl;

import com.ouharri.aftas.model.dto.requests.MemberRequest;
import com.ouharri.aftas.model.dto.responces.MemberResponse;
import com.ouharri.aftas.model.entities.Member;
import com.ouharri.aftas.model.mapper.MemberMapper;
import com.ouharri.aftas.repositories.MemberRepository;
import com.ouharri.aftas.services.spec.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Implementation of the {@link MemberService} interface.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImp extends _ServiceImp<UUID, MemberRequest, MemberResponse, Member, MemberRepository, MemberMapper> implements MemberService {

}
