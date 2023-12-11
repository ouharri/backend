package com.ouharri.aftas.services.spec;

import com.ouharri.aftas.model.dto.Level.LevelReq;
import com.ouharri.aftas.model.dto.Level.LevelResp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface LevelService {
    Optional<LevelResp> createLevel(LevelReq level);

}
