package com.ouharri.aftas.controllers;

import com.ouharri.aftas.model.dto.requests.LevelRequest;
import com.ouharri.aftas.model.dto.responces.LevelResponse;
import com.ouharri.aftas.services.spec.LevelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Controller class for handling CRUD operations on Level entities.
 * Exposes RESTful endpoints for managing Level entities, including creation, retrieval, update, and deletion.
 *
 * @see _Controller
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/level")
public class LevelController extends _Controller<UUID, LevelRequest, LevelResponse, LevelService> {

}