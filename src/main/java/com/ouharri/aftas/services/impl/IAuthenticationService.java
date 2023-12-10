package com.ouharri.aftas.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ouharri.aftas.exceptions.ResourceNotFoundException;
import com.ouharri.aftas.model.dto.auth.AuthenticationRequest;
import com.ouharri.aftas.model.dto.auth.AuthenticationResponse;
import com.ouharri.aftas.model.dto.auth.RegisterRequest;
import com.ouharri.aftas.model.entities.Token;
import com.ouharri.aftas.model.entities.User;
import com.ouharri.aftas.model.enums.Role;
import com.ouharri.aftas.model.enums.TokenType;
import com.ouharri.aftas.repositories.TokenRepository;
import com.ouharri.aftas.repositories.UserRepository;
import com.ouharri.aftas.security.JwtService;
import com.ouharri.aftas.services.spec.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;

/**
 * Service class for user authentication and token management.
 * This service provides methods to register a new user, authenticate a user, and refresh access tokens.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">ouharri outman</a>
 */
@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class IAuthenticationService implements AuthenticationService {
    private final JwtService jwtService;
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * Registers a new user and generates access and refresh tokens.
     *
     * @param request Registration request containing user details
     * @return AuthenticationResponse containing access and refresh tokens
     */
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();
        user.setRole(Role.USER);
        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * Authenticates a user and generates new access and refresh tokens.
     *
     * @param request Authentication request containing user credentials
     * @return AuthenticationResponse containing new access and refresh tokens
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );
        } catch (BadCredentialsException ex) {
            log.error("Authentication failed for user: " + request.email(), ex);
            throw new BadCredentialsException("Invalid credentials");
        } catch (AuthenticationException ex) {
            log.error("Authentication failed for user: " + request.email(), ex);
            throw new ResourceNotFoundException("Authentication failed");
        }
        var user = repository.findByEmail(request.email())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * Saves a new user token to the database.
     *
     * @param user     User for whom the token is generated
     * @param jwtToken JWT token to be saved
     */
    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    /**
     * Revokes all valid tokens for a user by marking them as expired and revoked.
     *
     * @param user User for whom tokens are revoked
     */
    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    /**
     * Refreshes the access token using a valid refresh token.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse for writing the new tokens
     * @throws IOException if an error occurs during response writing
     */
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    /**
     * Checks whether a token is valid.
     *
     * @param token Token to be checked
     * @return True if the token is valid, false otherwise
     */
    public Boolean checkToken(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return false;
        }
        String jwt = token.substring(7);
        var userEmail = jwtService.extractUsername(jwt);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow();
            return jwtService.isTokenValid(jwt, user);
        }
        return false;
    }
}
