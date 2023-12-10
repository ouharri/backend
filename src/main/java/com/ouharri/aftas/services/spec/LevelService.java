package com.ouharri.aftas.services.spec;

import com.ouharri.aftas.model.dto.Level.LevelReq;
import com.ouharri.aftas.model.dto.Level.LevelResp;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface LevelService {
    Optional<LevelResp> createLevel(LevelReq level);
}
