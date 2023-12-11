package com.ouharri.aftas.controllers;

import com.ouharri.aftas.exceptions.ResourceNotCreatedException;
import com.ouharri.aftas.model.dto.requests.Request;
import com.ouharri.aftas.model.dto.responces.Response;
import com.ouharri.aftas.services.spec._service;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

/**
 * Generic controller with CRUD operations for DTOs.
 *
 * @param <RequestType>  The request DTO type.
 * @param <ResponseType> The response DTO type.
 * @param <ServiceType>  The service type implementing _service.
 */
@Slf4j
@Validated
@AllArgsConstructor
@NoArgsConstructor(force = true)
public abstract class _controller<RequestType extends Request, ResponseType extends Response, ServiceType extends _service<RequestType, ResponseType>> {

    @Autowired
    protected final ServiceType service;

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

    @GetMapping
    public ResponseEntity<Page<ResponseType>> getAll(Pageable pageable) {
        assert service != null;
        return ResponseEntity.ok(service.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseType> getById(@PathVariable("id") UUID id) {
        assert service != null;
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

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

    @DeleteMapping
    public ResponseEntity<Void> delete(
            @Valid @RequestBody ResponseType request,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            handleValidationError(bindingResult);

        assert service != null;
        if (service.delete(request)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private void handleValidationError(BindingResult bindingResult) {
        throw new ResourceNotCreatedException(bindingResult);
    }
}