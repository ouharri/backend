package com.ouharri.aftas.services.impl;

import com.ouharri.aftas.repositories.CompetitionRepository;
import com.ouharri.aftas.services.spec.CompetitionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class ICompetitionService implements CompetitionService {
    private final CompetitionRepository repository;
}
