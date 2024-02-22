package com.ouharri.aftas.services.spec;

import com.ouharri.aftas.model.dto.requests.AuthenticationRequest;
import com.ouharri.aftas.model.dto.requests.RegisterRequest;
import com.ouharri.aftas.model.dto.responses.AuthenticationResponse;
import com.ouharri.aftas.model.enums.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Interface for user authentication and token management.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">ouharri outman</a>
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
     * Creates a new user based on the registration request and assigns a specified role.
     * This method involves setting up a new user with the provided details, encoding the password,
     * and saving the user to the database.
     * It then generates JWT access and refresh tokens for the
     * new user and saves these tokens.
     * This process is essential for registering new users and
     * providing them with the necessary tokens to access secured endpoints.
     *
     * @param request The registration request containing the new user's details such as name, email, and password.
     * @param role    The role to be assigned to the new user, which determines the user's permissions and access.
     * @return AuthenticationResponse containing the generated JWT access and refresh tokens.
     */
    AuthenticationResponse registerUser(RegisterRequest request, Role role);

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