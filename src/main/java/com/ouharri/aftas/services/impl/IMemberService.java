package com.ouharri.aftas.services.impl;

import com.ouharri.aftas.repositories.MemberRepository;
import com.ouharri.aftas.services.spec.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class IMemberService implements MemberService {
    private final MemberRepository repository;
}
