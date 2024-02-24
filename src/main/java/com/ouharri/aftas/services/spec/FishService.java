package com.ouharri.aftas.services.spec;

import com.ouharri.aftas.model.dto.requests.FishRequest;
import com.ouharri.aftas.model.dto.responses.FishResponse;

import java.util.UUID;

/**
 * Service interface for managing Fish entities.
 * Extends the generic service interface {@link _Service}.
 *
 * @see _Service
 */
public interface FishService extends _Service<UUID, FishRequest, FishResponse> {

}
