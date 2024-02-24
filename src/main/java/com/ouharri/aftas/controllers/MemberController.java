package com.ouharri.aftas.controllers;

import com.ouharri.aftas.model.dto.requests.MemberRequest;
import com.ouharri.aftas.model.dto.responses.MemberResponse;
import com.ouharri.aftas.services.spec.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Controller class for handling CRUD operations on Member entities.
 * Exposes RESTful endpoints for managing Member entities, including creation, retrieval, update, and deletion.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">ouharri outman</a>
 * @see _Controller
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/members")
public class MemberController extends _Controller<UUID, MemberRequest, MemberResponse, MemberService> {

}