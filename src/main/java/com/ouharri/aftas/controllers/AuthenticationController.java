package com.ouharri.aftas.controllers;

import com.ouharri.aftas.model.dto.AuthenticationRequest;
import com.ouharri.aftas.model.dto.AuthenticationResponse;
import com.ouharri.aftas.model.dto.RegisterRequest;
import com.ouharri.aftas.exceptions.ResourceNotCreatedException;
import com.ouharri.aftas.services.spec.IAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Validated
@RestController
@RequestMapping("/api/v2/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final IAuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody final RegisterRequest request,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            throw new ResourceNotCreatedException(bindingResult);

        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @Valid @RequestBody final AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/check-token")
    public ResponseEntity<Boolean> checkToken(
            HttpServletRequest request
    ) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        return ResponseEntity.ok(service.checkToken(authHeader));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }

}
