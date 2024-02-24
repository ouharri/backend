package com.ouharri.aftas.services.impl;

import com.ouharri.aftas.model.dto.requests.LevelRequest;
import com.ouharri.aftas.model.dto.responses.LevelResponse;
import com.ouharri.aftas.model.entities.Level;
import com.ouharri.aftas.mapper.LevelMapper;
import com.ouharri.aftas.repositories.LevelRepository;
import com.ouharri.aftas.services.spec.LevelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Implementation of the {@link LevelService} interface.
 */
@Slf4j
@Service
public class LevelServiceImpl extends _ServiceImp<UUID, LevelRequest, LevelResponse, Level, LevelRepository, LevelMapper> implements LevelService {

}
