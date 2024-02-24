package com.ouharri.aftas.services.impl;

import com.ouharri.aftas.model.dto.requests.FishRequest;
import com.ouharri.aftas.model.dto.responses.FishResponse;
import com.ouharri.aftas.model.entities.Fish;
import com.ouharri.aftas.mapper.FishMapper;
import com.ouharri.aftas.repositories.FishRepository;
import com.ouharri.aftas.services.spec.FishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Implementation of the {@link FishService} interface.
 */
@Slf4j
@Service
public class FishServiceImpl extends _ServiceImp<UUID, FishRequest, FishResponse, Fish, FishRepository, FishMapper> implements FishService {

}