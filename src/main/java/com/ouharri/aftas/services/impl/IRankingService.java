package com.ouharri.aftas.services.impl;

import com.ouharri.aftas.repositories.RankingRepository;
import com.ouharri.aftas.services.spec.RankingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class IRankingService implements RankingService {
    private final RankingRepository repository;
}
