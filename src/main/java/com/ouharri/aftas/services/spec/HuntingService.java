package com.ouharri.aftas.services.spec;

import com.ouharri.aftas.model.dto.requests.HuntingRequest;
import com.ouharri.aftas.model.dto.responses.HuntingResponse;

import java.util.Optional;
import java.util.UUID;

/**
 * Service interface for managing Hunting entities.
 * Extends the generic service interface {@link _Service}.
 *
 * @see _Service
 */
public interface HuntingService extends _Service<UUID, HuntingRequest, HuntingResponse> {

    /**
     * Increments the number of fish for a hunting record and returns the updated response.
     *
     * @param request The hunting response DTO containing the data to be updated.
     * @return An {@link Optional} containing the updated hunting response, or an empty {@link Optional} if the operation fails.
     */
    Optional<HuntingResponse> incrementNumberOfFish(HuntingResponse request);

    /**
     * Decrements the number of fish for a hunting record and returns the updated response.
     *
     * @param request The hunting response DTO containing the data to be updated.
     * @return An {@link Optional} containing the updated hunting response, or an empty {@link Optional} if the operation fails.
     */
    Optional<HuntingResponse> decrementNumberOfFish(HuntingResponse request);
}
