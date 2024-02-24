package com.ouharri.aftas.services.spec;

import com.ouharri.aftas.model.dto.requests.RankingRequest;
import com.ouharri.aftas.model.dto.responses.CompetitionResponse;
import com.ouharri.aftas.model.dto.responses.RankingResponse;
import com.ouharri.aftas.model.entities.Competition;
import com.ouharri.aftas.model.entities.Hunting;
import com.ouharri.aftas.model.entities.RankingId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service interface for managing Ranking entities.
 * Extends the generic service interface {@link _Service}.
 *
 * @see _Service
 */
public interface RankingService extends _Service<RankingId, RankingRequest, RankingResponse> {

    /**
     * Calculates and sets the ranking based on the given hunting data.
     *
     * @param hunting The hunting entity for which to calculate the ranking.
     */
    void calculateAndSetRanking(Hunting hunting);

    /**
     * Retrieves a paginated list of ranking responses for a specific competition.
     *
     * @param competition The competition for which to retrieve rankings.
     * @param pageable    The pageable information for pagination.
     * @return A paginated list of ranking responses.
     */
    Page<RankingResponse> getAllByCompetition(CompetitionResponse competition, Pageable pageable);

    /**
     * Calculates the ranking for the provided competition.
     *
     * @param competition The competition for which to calculate the ranking.
     */
    void calculateRanking(Competition competition);
}
