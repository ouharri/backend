package com.ouharri.aftas.controllers;

import com.ouharri.aftas.exceptions.ResourceNotCreatedException;
import com.ouharri.aftas.model.dto.Competition.CompetitionReq;
import com.ouharri.aftas.model.dto.Competition.CompetitionResp;
import com.ouharri.aftas.model.entities.Competition;
import com.ouharri.aftas.model.mapper.CompetitionMapper;
import com.ouharri.aftas.services.spec.CompetitionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/competition")
public class CompetitionController {
    private final ModelMapper modelMapper;
    private final CompetitionMapper competitionMapper;
    private final CompetitionService competitionService;

    @PostMapping
    public ResponseEntity<CompetitionResp> createLevel(
            @Valid @RequestBody CompetitionReq competition,
            BindingResult bindingResult
    ) {
        System.out.println("\n\n\n\n\n");
        System.out.println(competition);
        System.out.println("\n\n\n\n\n");
        System.out.println(modelMapper.map(competition, Competition.class).toString());
        System.out.println("\n\n\n\n\n");
        System.out.println(competitionMapper.toEntity(competition).toString());
        if (bindingResult.hasErrors())
            throw new ResourceNotCreatedException(bindingResult);

        Optional<CompetitionResp> CompetitionCreated = competitionService.createFish(competition);

        return CompetitionCreated.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.badRequest().build());
    }
}
