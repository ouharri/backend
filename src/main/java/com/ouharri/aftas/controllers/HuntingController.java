package com.ouharri.aftas.controllers;

import com.ouharri.aftas.model.dto.requests.HuntingRequest;
import com.ouharri.aftas.model.dto.responses.HuntingResponse;
import com.ouharri.aftas.services.spec.HuntingService;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

/**
 * Controller class for handling CRUD operations on Hunting entities.
 * Exposes RESTful endpoints for managing Hunting entities and provides
 * specific endpoints for incrementing and decrementing the number of fish in a hunting session.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">ouharri outman</a>
 * @see _Controller
 */
@Slf4j
@Builder
@Validated
@RestController
@RequestMapping("/api/v2/huntings")
public class HuntingController extends _Controller<UUID, HuntingRequest, HuntingResponse, HuntingService> {

    /**
     * Endpoint for incrementing the number of fish in a hunting session.
     *
     * @param request       The request DTO.
     * @param bindingResult The result of the validation.
     * @return ResponseEntity containing the updated hunting entity or a bad request if the operation fails.
     */
    @PutMapping("fish/increment")
    private ResponseEntity<HuntingResponse> incrementNumberOfFish(
            @Valid @RequestBody HuntingResponse request,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            handleValidationError(bindingResult);

        Optional<HuntingResponse> response = getService()
                .incrementNumberOfFish(request);

        return response.map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity.badRequest().build()
                );
    }

    /**
     * Endpoint for decrementing the number of fish in a hunting session.
     *
     * @param request       The request DTO.
     * @param bindingResult The result of the validation.
     * @return ResponseEntity containing the updated hunting entity or a bad request if the operation fails.
     */
    @PutMapping("fish/decrement")
    private ResponseEntity<HuntingResponse> decrementNumberOfFish(
            @Valid @RequestBody HuntingResponse request,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            handleValidationError(bindingResult);

        Optional<HuntingResponse> response = getService()
                .decrementNumberOfFish(request);

        return response.map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity.badRequest().build()
                );
    }
}