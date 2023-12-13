package com.ouharri.aftas.services.spec;

import com.ouharri.aftas.model.dto.requests.RankingRequest;
import com.ouharri.aftas.model.dto.responces.RankingResponse;
import com.ouharri.aftas.model.entities.Hunting;
import com.ouharri.aftas.model.entities.RankingId;

public interface RankingService extends _Service<RankingId,RankingRequest, RankingResponse> {
    void calculateAndSetRanking(Hunting hunting);
}
