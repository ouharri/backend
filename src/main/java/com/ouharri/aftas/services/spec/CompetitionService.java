package com.ouharri.aftas.services.spec;

import com.ouharri.aftas.model.dto.Competition.CompetitionReq;
import com.ouharri.aftas.model.dto.Competition.CompetitionResp;
import com.ouharri.aftas.model.entities.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CompetitionService {
    public Optional<CompetitionResp> createFish(CompetitionReq competition);

    public Page<CompetitionResp> getAllCompetitions(Pageable pageable);

}