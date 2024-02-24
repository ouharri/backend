package com.ouharri.aftas.controllers;

import com.ouharri.aftas.model.dto.Oauth.UrlDto;
import com.ouharri.aftas.model.dto.responses.AuthenticationResponse;
import com.ouharri.aftas.services.spec.OauthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling OAuth authentication processes.
 * This controller provides endpoints for getting the Google OAuth URL and
 * handling the callback from Google OAuth authentication.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">ouharri outman</a>
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/Oauth")
public class OauthController {
    private final OauthService service;

    /**
     * Endpoint for getting the Google OAuth authentication URL.
     * When accessed, it returns the URL that clients should be redirected to for Google authentication.
     *
     * @return ResponseEntity containing the Google OAuth URL wrapped in UrlDto.
     */
    @GetMapping("google/url")
    public ResponseEntity<UrlDto> googleAuth() {
        return ResponseEntity.ok(service.getGoogleAuthUrl());
    }

    /**
     * Endpoint for handling the callback from Google OAuth.
     * This endpoint is triggered when Google redirects back to the application after user authentication.
     * It processes the authorization code received from Google and performs authentication.
     *
     * @param code The authorization code provided by Google after user authentication.
     * @return ResponseEntity containing the authentication response (AuthenticationResponse).
     * Returns a 401 Unauthorized status in case of authentication failure.
     */
    @GetMapping("google/callback")
    public ResponseEntity<AuthenticationResponse> callback(@RequestParam("code") final String code) {
        try {
            return ResponseEntity.ok(service.authenticateFromGoogleCode(code));
        } catch (Exception e) {
            log.error("Authentication failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}