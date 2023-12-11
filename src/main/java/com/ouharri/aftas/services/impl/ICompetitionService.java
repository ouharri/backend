package com.ouharri.aftas.services.impl;

import com.ouharri.aftas.exceptions.ResourceNotCreatedException;
import com.ouharri.aftas.model.dto.Competition.CompetitionReq;
import com.ouharri.aftas.model.dto.Competition.CompetitionResp;
import com.ouharri.aftas.model.entities.Competition;
import com.ouharri.aftas.model.mapper.CompetitionMapper;
import com.ouharri.aftas.repositories.CompetitionRepository;
import com.ouharri.aftas.services.spec.CompetitionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class ICompetitionService implements CompetitionService {
    private final CompetitionRepository repository;
    private final CompetitionMapper mapper;

    @Transactional
    public Optional<CompetitionResp> createFish(CompetitionReq competition) {
        Competition CompetitionToCreate = mapper.toEntity(competition);
        try {
            Competition createdCompetition = repository.saveAndFlush(CompetitionToCreate);
            return Optional.of(mapper.toResp(createdCompetition));
        } catch (Exception e) {
            log.error("Error while creating Competition", e);
            throw new ResourceNotCreatedException(e.getMessage());
        }
    }
}
