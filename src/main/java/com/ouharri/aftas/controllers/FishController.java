package com.ouharri.aftas.controllers;

import com.ouharri.aftas.exceptions.ResourceNotCreatedException;
import com.ouharri.aftas.model.dto.Fish.FishReq;
import com.ouharri.aftas.model.dto.Fish.FishResp;
import com.ouharri.aftas.services.spec.FishService;
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
@RequestMapping("/api/v2/fish")
public class FishController {
    private final FishService fishService;

    @PostMapping
    public ResponseEntity<FishResp> createLevel(
            @Valid @RequestBody FishReq fish,
            BindingResult bindingResult
    ) {
        System.out.println("\n\n\n");
        System.out.println(fish);
        if (bindingResult.hasErrors())
            throw new ResourceNotCreatedException(bindingResult);

        Optional<FishResp> fishCreated = fishService.createFish(fish);

        return fishCreated.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.badRequest().build());
    }
}
