package com.ouharri.aftas.controllers;

import com.ouharri.aftas.services.spec.CompetitionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/auth")
public class CompetitionController {
    private final CompetitionService competitionService;
}
