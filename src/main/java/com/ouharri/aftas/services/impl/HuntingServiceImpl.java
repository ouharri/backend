package com.ouharri.aftas.services.impl;

import com.ouharri.aftas.events.HuntingCreatedEvent;
import com.ouharri.aftas.exceptions.ResourceNotCreatedException;
import com.ouharri.aftas.model.dto.requests.HuntingRequest;
import com.ouharri.aftas.model.dto.responses.HuntingResponse;
import com.ouharri.aftas.model.entities.Hunting;
import com.ouharri.aftas.mapper.HuntingMapper;
import com.ouharri.aftas.repositories.CompetitionRepository;
import com.ouharri.aftas.repositories.HuntingRepository;
import com.ouharri.aftas.services.spec.HuntingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of the {@link HuntingService} interface.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class HuntingServiceImpl extends _ServiceImp<UUID, HuntingRequest, HuntingResponse, Hunting, HuntingRepository, HuntingMapper> implements HuntingService {

    private final ApplicationEventPublisher eventPublisher;
    private final CompetitionRepository competitionRepository;

    /**
     * Creates a new hunting based on the provided request DTO.
     *
     * @param request DTO containing data for entity creation.
     * @return Optional containing the response DTO of the created entity.
     * @throws ResourceNotCreatedException If an error occurs during entity creation.
     */
    @Override
    public Optional<HuntingResponse> create(HuntingRequest request) {
        Hunting entityToCreate = mapper.toEntityFromRequest(request);

        if (competitionRepository.hasCompetitionNotStarted(entityToCreate.getHuntingCompositeKey().getCompetition().getId()))
            throw new ResourceNotCreatedException("The Competition didn't start yet.");

        if (competitionRepository.hasCompetitionEnded(entityToCreate.getHuntingCompositeKey().getCompetition().getId()))
            throw new ResourceNotCreatedException("The Competition ended.");

        if (repository.existsByHuntingCompositeKey(entityToCreate.getHuntingCompositeKey()))
            throw new ResourceNotCreatedException("The Hunting : " + entityToCreate.getHuntingCompositeKey().getFish().getName() + " for Member " + entityToCreate.getHuntingCompositeKey().getMember().getFirstname() + " already exists.");

        try {
            Hunting createdEntity = repository.saveAndFlush(entityToCreate);
            eventPublisher.publishEvent(new HuntingCreatedEvent(createdEntity));
            return Optional.of(mapper.toResponse(createdEntity));
        } catch (Exception e) {
            log.error("Error while creating entity", e);
            throw new ResourceNotCreatedException(e.getMessage());
        }
    }

    /**
     * Increments the number of fish for a hunting based on the provided response DTO.
     *
     * @param request DTO containing data for entity modification.
     * @return Optional containing the response DTO of the modified entity.
     * @throws ResourceNotCreatedException If an error occurs during entity modification.
     */
    public Optional<HuntingResponse> incrementNumberOfFish(HuntingResponse request) {
        Hunting entityToUpdate = mapper.toEntityFromResponse(request);

        if (!repository.existsByHuntingCompositeKey(entityToUpdate.getHuntingCompositeKey()))
            throw new ResourceNotCreatedException("The Hunting doesn't exist.");

        if (competitionRepository.hasCompetitionNotStarted(entityToUpdate.getHuntingCompositeKey().getCompetition().getId()))
            throw new ResourceNotCreatedException("The Competition didn't start yet.");

        if (competitionRepository.hasCompetitionEnded(entityToUpdate.getHuntingCompositeKey().getCompetition().getId()))
            throw new ResourceNotCreatedException("The Competition ended.");

        try {
            entityToUpdate.setNumberOfFish(entityToUpdate.getNumberOfFish() + 1);
            Hunting createdEntity = repository.saveAndFlush(entityToUpdate);
            eventPublisher.publishEvent(new HuntingCreatedEvent(createdEntity));
            return Optional.of(mapper.toResponse(createdEntity));
        } catch (Exception e) {
            log.error("Error while creating entity", e);
            throw new ResourceNotCreatedException(e.getMessage());
        }
    }

    /**
     * Decrements the number of fish for a hunting based on the provided response DTO.
     *
     * @param request DTO containing data for entity modification.
     * @return Optional containing the response DTO of the modified entity.
     * @throws ResourceNotCreatedException If an error occurs during entity modification.
     */
    public Optional<HuntingResponse> decrementNumberOfFish(HuntingResponse request) {
        Hunting entityToUpdate = mapper.toEntityFromResponse(request);

        if (competitionRepository.hasCompetitionNotStarted(entityToUpdate.getHuntingCompositeKey().getCompetition().getId()))
            throw new ResourceNotCreatedException("The Competition didn't start yet.");

        if (competitionRepository.hasCompetitionEnded(entityToUpdate.getHuntingCompositeKey().getCompetition().getId()))
            throw new ResourceNotCreatedException("The Competition ended.");

        if (!repository.existsByHuntingCompositeKey(entityToUpdate.getHuntingCompositeKey()))
            throw new ResourceNotCreatedException("The Hunting doesn't exist.");

        try {
            entityToUpdate.setNumberOfFish(entityToUpdate.getNumberOfFish() - 1);
            Hunting createdEntity = repository.saveAndFlush(entityToUpdate);
            eventPublisher.publishEvent(new HuntingCreatedEvent(createdEntity));
            return Optional.of(mapper.toResponse(createdEntity));
        } catch (Exception e) {
            log.error("Error while creating entity", e);
            throw new ResourceNotCreatedException(e.getMessage());
        }
    }
}