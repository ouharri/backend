package com.ouharri.aftas.controllers;

import com.ouharri.aftas.exceptions.ResourceNotCreatedException;
import com.ouharri.aftas.model.dto.requests.AuthenticationRequest;
import com.ouharri.aftas.model.dto.requests.RegisterRequest;
import com.ouharri.aftas.model.dto.responses.AuthenticationResponse;
import com.ouharri.aftas.services.spec.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Controller class for handling authentication-related requests.
 * This class provides endpoints for user registration, authentication,
 * token validation, and token refreshing.
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">Ouharri Outman</a>
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/auth")
public class AuthenticationController {
    private final AuthenticationService service;

    /**
     * Endpoint for user registration.
     *
     * @param request       The registration request containing user details.
     * @param bindingResult BindingResult for validation errors.
     * @return ResponseEntity with the registered user's authentication response.
     * @throws ResourceNotCreatedException If there are validation errors.
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody final RegisterRequest request,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            throw new ResourceNotCreatedException(bindingResult);

        return ResponseEntity.ok(service.register(request));
    }

    /**
     * Endpoint for user authentication.
     *
     * @param request       The authentication request containing user credentials.
     * @param bindingResult BindingResult for validation errors.
     * @return ResponseEntity with the authenticated user's response.
     * @throws ResourceNotCreatedException If there are validation errors.
     */
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @Valid @RequestBody final AuthenticationRequest request,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            throw new ResourceNotCreatedException(bindingResult);

        return ResponseEntity.ok(service.authenticate(request));
    }

    /**
     * Endpoint for checking the validity of an authentication token.
     *
     * @param request The HTTPServletRequest containing the token in the Authorization header.
     * @return ResponseEntity with a boolean indicating token validity.
     */
    @PostMapping("/check-token")
    public ResponseEntity<Boolean> checkToken(
            HttpServletRequest request
    ) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        return ResponseEntity.ok(service.checkToken(authHeader));
    }

    /**
     * Endpoint for refreshing an authentication token.
     *
     * @param request  The HTTPServletRequest containing the current token.
     * @param response The HTTPServletResponse for sending the new token.
     * @throws IOException If there is an issue handling the token refresh.
     */
    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }

}