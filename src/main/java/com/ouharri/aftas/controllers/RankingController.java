package com.ouharri.aftas.controllers;

import com.ouharri.aftas.model.dto.requests.RankingRequest;
import com.ouharri.aftas.model.dto.responses.CompetitionResponse;
import com.ouharri.aftas.model.dto.responses.RankingResponse;
import com.ouharri.aftas.model.entities.RankingId;
import com.ouharri.aftas.services.spec.RankingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling CRUD operations on Ranking entities.
 * Exposes RESTful endpoints for managing Ranking entities, including creation, retrieval, update, and deletion.
 * Additionally, provides an endpoint for retrieving rankings based on a specific competition.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">ouharri outman</a>
 * @see _Controller
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/rankings")
public class RankingController extends _Controller<RankingId, RankingRequest, RankingResponse, RankingService> {

    /**
     * Retrieves rankings based on a specific competition.
     *
     * @param request       The request DTO containing information about the competition.
     * @param pageable      The pagination information.
     * @param bindingResult The result of the validation.
     * @return ResponseEntity containing a page of RankingResponse entities for the specified competition.
     */
    @PostMapping("/competition")
    public ResponseEntity<Page<RankingResponse>> getRankingByCompetition(
            @Valid @RequestBody CompetitionResponse request,
            Pageable pageable,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            handleValidationError(bindingResult);

        return ResponseEntity.ok(
                getService().getAllByCompetition(request, pageable)
        );
    }
}