package com.ouharri.aftas.services.spec;

import com.ouharri.aftas.model.dto.requests.AuthenticationRequest;
import com.ouharri.aftas.model.dto.requests.RegisterRequest;
import com.ouharri.aftas.model.dto.responces.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Interface for user authentication and token management.
 */
public interface AuthenticationService {

    /**
     * Registers a new user and generates access and refresh tokens.
     *
     * @param request Registration request containing user details
     * @return AuthenticationResponse containing access and refresh tokens
     */
    AuthenticationResponse register(RegisterRequest request);

    /**
     * Authenticates a user and generates new access and refresh tokens.
     *
     * @param request Authentication request containing user credentials
     * @return AuthenticationResponse containing new access and refresh tokens
     */
    AuthenticationResponse authenticate(AuthenticationRequest request);

    /**
     * Refreshes the access token using a valid refresh token.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse for writing the new tokens
     * @throws IOException if an error occurs during response writing
     */
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

    /**
     * Checks if a token is valid.
     *
     * @param token Token to check
     * @return true if the token is valid, false otherwise
     */
    Boolean checkToken(String token);
}

