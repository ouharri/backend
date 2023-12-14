package com.ouharri.aftas.controllers;

import com.ouharri.aftas.model.dto.requests.CompetitionRequest;
import com.ouharri.aftas.model.dto.responces.CompetitionResponse;
import com.ouharri.aftas.services.spec.CompetitionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Controller class for handling CRUD operations on Competition entities.
 * Exposes RESTful endpoints for managing Competition entities.
 *
 * @see _Controller
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/competition")
public class CompetitionController extends _Controller<UUID, CompetitionRequest, CompetitionResponse, CompetitionService> {

}
