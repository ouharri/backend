package com.ouharri.aftas.services.spec;

import com.ouharri.aftas.model.dto.Fish.FishReq;
import com.ouharri.aftas.model.dto.Fish.FishResp;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface FishService {
    public Optional<FishResp> createFish(FishReq fish);
}
