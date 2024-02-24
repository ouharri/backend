package com.ouharri.aftas.controllers;

import com.ouharri.aftas.model.dto.requests.FishRequest;
import com.ouharri.aftas.model.dto.responses.FishResponse;
import com.ouharri.aftas.services.spec.FishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Controller class for handling CRUD operations on Fish entities.
 * Exposes RESTful endpoints for managing Fish entities.
 *
 * @see _Controller
 */
@Slf4j
@Validated
@RestController
@RequestMapping("api/v2/fishs")
public class FishController extends _Controller<UUID, FishRequest, FishResponse, FishService> {

}