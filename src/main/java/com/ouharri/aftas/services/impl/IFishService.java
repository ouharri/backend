package com.ouharri.aftas.services.impl;

import com.ouharri.aftas.exceptions.ResourceNotCreatedException;
import com.ouharri.aftas.model.dto.Fish.FishReq;
import com.ouharri.aftas.model.dto.Fish.FishResp;
import com.ouharri.aftas.model.entities.Fish;
import com.ouharri.aftas.model.mapper.FishMapper;
import com.ouharri.aftas.repositories.FishRepository;
import com.ouharri.aftas.services.spec.FishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class IFishService implements FishService {
    private final FishRepository repository;
    private final FishMapper mapper;

    public Optional<FishResp> createFish(FishReq fish) {
        Fish fishToCreate = mapper.toEntity(fish);
        System.out.println("\n\n\n");
        System.out.println(fishToCreate.toString());
        try {
            Fish createdFish = repository.save(fishToCreate);
            return Optional.of(mapper.toResp(createdFish));
        } catch (Exception e) {
            log.error("Error while creating fish", e);
            throw new ResourceNotCreatedException(e.getMessage());
        }
    }
}