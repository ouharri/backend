package com.ouharri.aftas.controllers;

import com.ouharri.aftas.exceptions.ResourceNotCreatedException;
import com.ouharri.aftas.model.dto.App.RestPage;
import com.ouharri.aftas.model.dto.requests._Request;
import com.ouharri.aftas.model.dto.responses._Response;
import com.ouharri.aftas.services.spec._Service;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Generic controller with CRUD operations for DTOs.
 *
 * @param <ID>           The type of the identifier.
 * @param <RequestType>  The request DTO type.
 * @param <ResponseType> The response DTO type.
 * @param <ServiceType>  The service type implementing _service.
 * @author <a href="mailto:ouharrioutman@gmail.com">ouharri outman</a>
 */
@Slf4j
@Getter
@Validated
@RestController
@AllArgsConstructor
@NoArgsConstructor(force = true)
public abstract class _Controller<ID, RequestType extends _Request, ResponseType extends _Response, ServiceType extends _Service<ID, RequestType, ResponseType>> {

    ServiceType service;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public final void setService(ServiceType service) {
        this.service = service;
    }

    /**
     * Creates a new entity based on the provided request.
     *
     * @param request       The request DTO.
     * @param bindingResult The result of the validation.
     * @return ResponseEntity containing the created entity or a bad request if creation fails.
     */
    @PostMapping
    public ResponseEntity<ResponseType> create(
            @Valid @RequestBody RequestType request,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            handleValidationError(bindingResult);

        assert service != null;
        Optional<ResponseType> response = service.create(request);

        return response.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.badRequest().build());
    }

    /**
     * Retrieves all entities with pagination.
     *
     * @param pageable The pagination information.
     * @return ResponseEntity containing a page of entities.
     */
    @GetMapping("/paged")
    public ResponseEntity<RestPage<ResponseType>> getAll(Pageable pageable) {
        assert service != null;
        return ResponseEntity.ok(service.getAll(pageable));
    }

    /**
     * Retrieves all entities.
     *
     * @return ResponseEntity containing a list of entities.
     */
    @GetMapping
    public ResponseEntity<List<ResponseType>> getAll() {
        assert service != null;
        return ResponseEntity.ok(service.getAll());
    }


    /**
     * Retrieves an entity by its identifier.
     *
     * @param id The identifier of the entity.
     * @return ResponseEntity containing the retrieved entity or not found response if the entity does not exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseType> getById(@Valid @PathVariable("id") ID id) {
        assert service != null;
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Updates an existing entity based on the provided request.
     *
     * @param request       The request DTO.
     * @param bindingResult The result of the validation.
     * @return ResponseEntity containing the updated entity or not found response if the update fails.
     */
    @PutMapping
    public ResponseEntity<ResponseType> update(
            @Valid @RequestBody ResponseType request,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            handleValidationError(bindingResult);

        assert service != null;
        Optional<ResponseType> updated = service.update(request);

        return updated.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.notFound().build());
    }

    /**
     * Deletes an entity based on the provided request.
     *
     * @param request       The request DTO.
     * @param bindingResult The result of the validation.
     * @return ResponseEntity with no content if deletion is successful, or not found response if deletion fails.
     */
    @DeleteMapping
    public ResponseEntity<Void> delete(
            @Valid @RequestBody ResponseType request,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            handleValidationError(bindingResult);

        assert service != null;
        if (service.delete(request)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Handles validation errors by throwing a {@link ResourceNotCreatedException}.
     *
     * @param bindingResult The result of the validation.
     */
    protected void handleValidationError(BindingResult bindingResult) {
        throw new ResourceNotCreatedException(bindingResult);
    }
}