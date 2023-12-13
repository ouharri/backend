package com.ouharri.aftas.services.impl;

import com.ouharri.aftas.exceptions.ResourceNotCreatedException;
import com.ouharri.aftas.model.dto.requests._Request;
import com.ouharri.aftas.model.dto.responces._Response;
import com.ouharri.aftas.model.entities._Entity;
import com.ouharri.aftas.model.mapper._Mapper;
import com.ouharri.aftas.services.spec._Service;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

/**
 * Generic service implementation with common CRUD operations.
 *
 * @param <Req>        The request DTO type.
 * @param <Res>        The response DTO type.
 * @param <Entity>     The entity type.
 * @param <Repository> The repository type extending JpaRepository<Entity, UUID>.
 * @param <Mapper>     The mapper type implementing _Mapper<Req, Res, Entity>.
 */
@Slf4j
@Validated
@AllArgsConstructor
@NoArgsConstructor(force = true)
public abstract class _ServiceImp<ID, Req extends _Request, Res extends _Response, Entity extends _Entity<ID>, Repository extends JpaRepository<Entity, ID>, Mapper extends _Mapper<ID, Req, Res, Entity>> implements _Service<ID, Req, Res> {

    Mapper mapper;
    Repository repository;

    @Autowired
    public final void setRepository(Repository repository) {
        this.repository = repository;
    }

    @Autowired
    public final void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Retrieves all entities in a paginated form.
     *
     * @param pageable Pagination information.
     * @return Page of response DTOs.
     */
    public Page<Res> getAll(Pageable pageable) {
        assert repository != null;
        assert mapper != null;
        return repository.findAll(pageable)
                .map(mapper::toResponse);
    }

    /**
     * Creates a new entity based on the provided request DTO.
     *
     * @param request DTO containing data for entity creation.
     * @return Optional containing the response DTO of the created entity.
     */
    @Transactional
    public Optional<Res> create(Req request) {
        assert mapper != null;
        Entity entityToCreate = mapper.toEntityFromRequest(request);
        try {
            assert repository != null;
            Entity createdEntity = repository.saveAndFlush(entityToCreate);
            return Optional.of(mapper.toResponse(createdEntity));
        } catch (Exception e) {
            log.error("Error while creating entity", e);
            throw new ResourceNotCreatedException(e.getMessage());
        }
    }

    /**
     * Updates an existing entity based on the provided response DTO.
     *
     * @param response DTO containing updated data.
     * @return Optional containing the response DTO of the updated entity.
     */
    @Transactional
    public Optional<Res> update(Res response) {
        assert mapper != null;
        Entity entityToUpdate = mapper.toEntityFromResponse(response);
        try {
            assert repository != null;
            Entity updatedEntity = repository.saveAndFlush(entityToUpdate);
            return Optional.of(mapper.toResponse(updatedEntity));
        } catch (Exception e) {
            log.error("Error while updating entity", e);
            throw new ResourceNotCreatedException(e.getMessage());
        }
    }

    /**
     * Retrieves an entity by its unique identifier.
     *
     * @param id Unique identifier of the entity.
     * @return Optional containing the response DTO of the found entity.
     */
    public Optional<Res> getById(ID id) {
        assert repository != null;
        assert mapper != null;
        return repository.findById(id)
                .map(mapper::toResponse);
    }

    /**
     * Deletes an entity based on the provided response DTO.
     *
     * @param response DTO containing data for entity deletion.
     * @return Boolean indicating the success of the deletion operation.
     */
    @Transactional
    public Boolean delete(Res response) {
        assert mapper != null;
        Entity entityToDelete = mapper.toEntityFromResponse(response);
        System.out.println("\n\n\nentityToDelete");
        System.out.println(entityToDelete);
        assert repository != null;
        if (!repository.existsById(entityToDelete.getId())) {
            return false;
        }
        try {
            repository.delete(entityToDelete);
        } catch (Exception e) {
            log.error("Error while deleting entity", e);
            throw new ResourceNotCreatedException(e.getMessage());
        }
        return true;
    }
}