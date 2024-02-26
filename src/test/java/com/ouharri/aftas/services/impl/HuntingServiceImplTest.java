package com.ouharri.aftas.services.impl;

import com.ouharri.aftas.model.dto.requests.HuntingRequest;
import com.ouharri.aftas.model.dto.responses.*;
import com.ouharri.aftas.repositories.CompetitionRepository;
import com.ouharri.aftas.repositories.HuntingRepository;
import com.ouharri.aftas.model.entities.Hunting;
import com.ouharri.aftas.mapper.HuntingMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HuntingServiceImplTest {

    @Mock
    private HuntingRepository huntingRepository;

    @Mock
    private HuntingMapper huntingMapper;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @Mock
    private CompetitionRepository competitionRepository;

    @InjectMocks
    private HuntingServiceImpl huntingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {
        LevelResponse levelResponse = new LevelResponse();
        levelResponse.setId(UUID.randomUUID());
        levelResponse.setPoints(10);
        levelResponse.setName("Level");
        levelResponse.setDescription("Description");

        FishResponse fishResponse = new FishResponse();
        fishResponse.setId(UUID.randomUUID());
        fishResponse.setName("Fish");
        fishResponse.setLevel(levelResponse);

        MemberResponse memberResponse = new MemberResponse();
        memberResponse.setId(UUID.randomUUID());
        memberResponse.setFirstname("Member");
        memberResponse.setLastname("Surname");

        CompetitionResponse competitionResponse = new CompetitionResponse();
        competitionResponse.setId(UUID.randomUUID());
        competitionResponse.setLocation("Competition");


        HuntingRequest huntingRequest = new HuntingRequest(
                10,
                fishResponse,
                memberResponse,
                competitionResponse
        );

        Hunting entityToCreate = new Hunting();
        HuntingResponse expectedResponse = new HuntingResponse();

        when(huntingMapper.toEntityFromRequest(huntingRequest)).thenReturn(entityToCreate);
        when(competitionRepository.hasCompetitionNotStarted(any())).thenReturn(false);
        when(competitionRepository.hasCompetitionEnded(any())).thenReturn(false);
        when(huntingRepository.existsByHuntingCompositeKey(any())).thenReturn(false);
        when(huntingRepository.saveAndFlush(entityToCreate)).thenReturn(entityToCreate);
        when(huntingMapper.toResponse(entityToCreate)).thenReturn(expectedResponse);

        Optional<HuntingResponse> result = huntingService.create(huntingRequest);

        assertTrue(result.isPresent());
        assertEquals(expectedResponse, result.get());

        assertEquals(100, entityToCreate.getNumberOfFish() * entityToCreate.getHuntingCompositeKey().getFish().getLevel().getPoints());

        verify(eventPublisher, times(1)).publishEvent(any());
    }

    @Test
    void incrementNumberOfFish() {

    }

    @Test
    void decrementNumberOfFish() {

    }
}
