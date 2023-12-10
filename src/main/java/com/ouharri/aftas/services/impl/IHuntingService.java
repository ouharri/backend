package com.ouharri.aftas.services.impl;

import com.ouharri.aftas.repositories.HuntingRepository;
import com.ouharri.aftas.services.spec.HuntingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class IHuntingService implements HuntingService {
    private final HuntingRepository repository;
}
