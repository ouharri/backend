package com.ouharri.aftas.services.spec;

import com.ouharri.aftas.model.dto.Oauth.TokenDto;
import com.ouharri.aftas.model.dto.Oauth.UrlDto;
import com.ouharri.aftas.model.dto.responses.AuthenticationResponse;

import java.io.IOException;

/**
 * Interface defining the contract for OAuth services, particularly focusing on Google OAuth authentication.
 * This interface outlines methods for generating a Google OAuth URL, retrieving a Google authentication token,
 * and authenticating a user based on a Google authentication code.
 *  @author <a href="mailto:ouharrioutman@gmail.com">ouharri outman</a>
 */
public interface OauthService {

    /**
     * Generates a URL for initiating Google OAuth authentication.
     * This URL is used to redirect users to Google's OAuth service for authentication purposes.
     *
     * @return A UrlDto containing the URL to redirect users to Google's OAuth service.
     */
    UrlDto getGoogleAuthUrl();

    /**
     * Retrieves a Google authentication token based on the provided authorization code.
     * This method is invoked after the user has authenticated with Google and an authorization code is received.
     *
     * @param code The authorization code received from Google after user consent.
     * @return A TokenDto containing the Google authentication token.
     * @throws IOException If an error occurs during the token retrieval process.
     */
    TokenDto getGoogleTokenAuthentification(String code) throws IOException;

    /**
     * Authenticates a user with a Google authentication code.
     * This method processes the authentication code received from Google to authenticate the user
     * and provide necessary access and refresh tokens.
     *
     * @param code The Google authentication code.
     * @return AuthenticationResponse containing access and refresh tokens for the authenticated user.
     * @throws IOException If an error occurs during the authentication process.
     */
    AuthenticationResponse authenticateFromGoogleCode(String code) throws IOException;
}