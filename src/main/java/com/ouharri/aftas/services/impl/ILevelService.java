package com.ouharri.aftas.services.impl;

import com.ouharri.aftas.exceptions.ResourceNotCreatedException;
import com.ouharri.aftas.model.dto.Level.LevelReq;
import com.ouharri.aftas.model.dto.Level.LevelResp;
import com.ouharri.aftas.model.entities.Level;
import com.ouharri.aftas.model.mapper.LevelMapper;
import com.ouharri.aftas.repositories.LevelRepository;
import com.ouharri.aftas.services.spec.LevelService;
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
public class ILevelService implements LevelService {
    private final LevelRepository repository;
    private final LevelMapper mapper;

    @Transactional
    public Optional<LevelResp> createLevel(LevelReq level) {
        Level levelToCreate = mapper.toEntity(level);
        try {
            Level createdLevel = repository.save(levelToCreate);
            return Optional.of(mapper.toResp(createdLevel));
        } catch (Exception e) {
            log.error("Error while creating level", e);
            throw new ResourceNotCreatedException(e.getMessage());
        }
    }
}
