package com.ouharri.aftas.services.spec;

import com.ouharri.aftas.model.dto.Competition.CompetitionReq;
import com.ouharri.aftas.model.dto.Competition.CompetitionResp;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CompetitionService {
    public Optional<CompetitionResp> createFish(CompetitionReq competition);
}