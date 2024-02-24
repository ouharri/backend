package com.ouharri.aftas.services.spec;

import com.ouharri.aftas.model.dto.requests.CompetitionRequest;
import com.ouharri.aftas.model.dto.responses.CompetitionResponse;
import com.ouharri.aftas.model.entities.Competition;

import java.util.Optional;
import java.util.UUID;

/**
 * Service interface for managing {@link Competition} entities.
 * Extends the generic service interface {@link _Service}.
 *
 * @see _Service
 */
public interface CompetitionService extends _Service<UUID, CompetitionRequest, CompetitionResponse> {

    /**
     * Retrieves a competition by its unique identifier.
     *
     * @param id The unique identifier of the competition.
     * @return An optional containing the competition, or empty if not found.
     */
    Optional<Competition> getCompetitionById(UUID id);
}
