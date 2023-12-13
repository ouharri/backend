package com.ouharri.aftas.services.spec;

import com.ouharri.aftas.model.dto.requests._Request;
import com.ouharri.aftas.model.dto.responces._Response;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Generic service interface with common CRUD operations.
 *
 * @param <Req> The request DTO type.
 * @param <Res> The response DTO type.
 */
public interface _Service<ID, Req extends _Request, Res extends _Response> {

    /**
     * Retrieves all entities in a paginated form.
     *
     * @param pageable Pagination information.
     * @return Page of response DTOs.
     */
    Page<Res> getAll(Pageable pageable);

    /**
     * Creates a new entity based on the provided request DTO.
     *
     * @param request DTO containing data for entity creation.
     * @return Optional containing the response DTO of the created entity.
     */
    Optional<Res> create(@Valid Req request);

    /**
     * Updates an existing entity based on the provided response DTO.
     *
     * @param response DTO containing updated data.
     * @return Optional containing the response DTO of the updated entity.
     */
    Optional<Res> update(@Valid Res response);

    /**
     * Retrieves an entity by its unique identifier.
     *
     * @param id Unique identifier of the entity.
     * @return Optional containing the response DTO of the found entity.
     */
    Optional<Res> getById(ID id);

    /**
     * Deletes an entity based on the provided response DTO.
     *
     * @param response DTO containing data for entity deletion.
     * @return Boolean indicating the success of the deletion operation.
     */
    Boolean delete(@Valid Res response);
}