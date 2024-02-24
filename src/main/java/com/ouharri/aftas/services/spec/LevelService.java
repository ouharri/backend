package com.ouharri.aftas.services.spec;

import com.ouharri.aftas.model.dto.requests.LevelRequest;
import com.ouharri.aftas.model.dto.responses.LevelResponse;

import java.util.UUID;

/**
 * Service interface for managing Level entities.
 * Extends the generic service interface {@link _Service}.
 *
 * @see _Service
 */
public interface LevelService extends _Service<UUID, LevelRequest, LevelResponse> {

}
