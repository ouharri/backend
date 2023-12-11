package com.ouharri.aftas.controllers;

import com.ouharri.aftas.exceptions.ResourceNotCreatedException;
import com.ouharri.aftas.model.dto.Competition.CompetitionReq;
import com.ouharri.aftas.model.dto.Competition.CompetitionResp;
import com.ouharri.aftas.services.spec.CompetitionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/competition")
public class CompetitionController {
    private final CompetitionService service;

    @PostMapping
    public ResponseEntity<CompetitionResp> createLevel(
            @Valid @RequestBody CompetitionReq competition,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            throw new ResourceNotCreatedException(bindingResult);

        Optional<CompetitionResp> CompetitionCreated = service.createFish(competition);

        return CompetitionCreated.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<Page<CompetitionResp>> getAllLevels(Pageable pageable) {
        return ResponseEntity.ok(
                service.getAllCompetitions(pageable)
        );
    }
}
