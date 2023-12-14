package com.ouharri.aftas.services.spec;

import com.ouharri.aftas.model.dto.requests.MemberRequest;
import com.ouharri.aftas.model.dto.responces.MemberResponse;

import java.util.UUID;

/**
 * Service interface for managing Member entities.
 * Extends the generic service interface {@link _Service}.
 *
 * @see _Service
 */
public interface MemberService extends _Service<UUID, MemberRequest, MemberResponse> {

}
