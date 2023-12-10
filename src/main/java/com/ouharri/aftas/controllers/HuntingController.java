package com.ouharri.aftas.controllers;

import com.ouharri.aftas.services.spec.HuntingService;
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
public class HuntingController {
    private final HuntingService huntingService;
}
