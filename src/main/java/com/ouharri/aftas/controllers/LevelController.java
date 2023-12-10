package com.ouharri.aftas.controllers;

import com.ouharri.aftas.exceptions.ResourceNotCreatedException;
import com.ouharri.aftas.model.dto.Level.LevelReq;
import com.ouharri.aftas.model.dto.Level.LevelResp;
import com.ouharri.aftas.services.spec.LevelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/api/v2/level")
public class LevelController {
    private final LevelService levelService;

    @PostMapping
    public ResponseEntity<LevelResp> createLevel(
            @Valid @RequestBody LevelReq level,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            throw new ResourceNotCreatedException(bindingResult);

        Optional<LevelResp> levelToCreate = levelService.createLevel(level);

        return levelToCreate.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.badRequest().build());
    }
}
