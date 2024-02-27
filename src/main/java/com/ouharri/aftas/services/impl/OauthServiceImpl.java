package com.ouharri.aftas.services.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.ouharri.aftas.model.dto.Oauth.TokenDto;
import com.ouharri.aftas.model.dto.Oauth.UrlDto;
import com.ouharri.aftas.model.dto.responses.AuthenticationResponse;
import com.ouharri.aftas.model.entities.User;
import com.ouharri.aftas.model.enums.Role;
import com.ouharri.aftas.model.enums.UserStatus;
import com.ouharri.aftas.repositories.UserRepository;
import com.ouharri.aftas.services.spec.JwtService;
import com.ouharri.aftas.services.spec.OauthService;
import com.ouharri.aftas.services.spec.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;

/**
 * Implementation of the OauthService interface.
 * This service handles the OAuth authentication process, particularly for Google OAuth.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">ouharri outman</a>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OauthServiceImpl implements OauthService {
    
    private final JwtService jwtService;
    private final UserService userService;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    private final String googleAuthRedirectUri = "http://localhost:4200/auth/Oauth/callback";

    @Value("${spring.security.oauth2.resourceserver.opaque-token.clientId}")
    private String clientId;

    @Value("${spring.security.oauth2.resourceserver.opaque-token.clientSecret}")
    private String clientSecret;

    /**
     * Generates a URL for Google OAuth authentication.
     *
     * @return A UrlDto containing the Google OAuth URL.
     */
    public UrlDto getGoogleAuthUrl() {
        log.info("Generating Google OAuth URL");
        return new UrlDto(
                new GoogleAuthorizationCodeRequestUrl(
                        clientId,
                        googleAuthRedirectUri,
                        Arrays.asList("email", "profile", "openid")
                ).build()
        );
    }

    /**
     * Retrieves a Google authentication token based on the provided code.
     *
     * @param code The authorization code from Google.
     * @return A TokenDto containing the Google authentication token.
     * @throws IOException If an error occurs during the token retrieval.
     */
    public TokenDto getGoogleTokenAuthentification(String code) throws IOException {
        log.info("Retrieving Google authentication token for code: {}", code);
        try {
            String Token = new GoogleAuthorizationCodeTokenRequest(
                    new NetHttpTransport(),
                    new GsonFactory(),
                    clientId,
                    clientSecret,
                    code,
                    googleAuthRedirectUri
            )
                    .execute()
                    .getAccessToken();

            return new TokenDto(Token);
        } catch (Exception e) {
            log.error("Error retrieving Google authentication token: {}", e.getMessage());
            throw new IOException(e);
        }
    }

    /**
     * Authenticates a user with a Google authentication code.
     *
     * @param code The Google authentication code.
     * @return AuthenticationResponse containing access and refresh tokens.
     * @throws IOException If an error occurs during authentication.
     */
    @Transactional
    public AuthenticationResponse authenticateFromGoogleCode(String code) throws IOException {
        log.info("Authenticating user with Google code: {}", code);

        GoogleIdToken.Payload payload = getGooglePayload(code);
        User user = getUserFromPayload(payload);
        user.setStatus(UserStatus.ONLINE);
        repository.save(user);

        return createAuthenticationResponse(user);
    }

    /**
     * Retrieves the payload from a Google ID Token based on the provided authentication code.
     * This method combines the process of fetching the Google Token Response and parsing the
     * Google ID Token to extract the payload, which contains user information.
     *
     * @param code The Google authentication code.
     * @return The payload of the Google ID Token.
     * @throws IOException If an error occurs while fetching or parsing the token.
     */
    private GoogleIdToken.Payload getGooglePayload(String code) throws IOException {
        GoogleTokenResponse tokenResponse = fetchGoogleTokenResponse(code);
        return parseGoogleIdToken(tokenResponse);
    }

    /**
     * Fetches Google Token Response using the provided Google authentication code.
     *
     * @param code The Google authentication code.
     * @return GoogleTokenResponse object containing the token information.
     * @throws IOException If there is an error in fetching the token response.
     */
    private GoogleTokenResponse fetchGoogleTokenResponse(String code) throws IOException {
        try {
            return new GoogleAuthorizationCodeTokenRequest(
                    new NetHttpTransport(),
                    new GsonFactory(),
                    clientId,
                    clientSecret,
                    code,
                    googleAuthRedirectUri
            ).execute();
        } catch (IOException e) {
            log.error("Error fetching Google token response: {}", e.getMessage());
            throw new IOException("Failed to fetch Google token response", e);
        }
    }

    /**
     * Parses the Google ID Token from the Google Token Response.
     *
     * @param tokenResponse The response from Google containing the ID token.
     * @return Payload of the parsed Google ID Token.
     * @throws IOException If there is an error in parsing the ID token.
     */
    private GoogleIdToken.Payload parseGoogleIdToken(GoogleTokenResponse tokenResponse) throws IOException {
        try {
            GoogleIdToken idToken = tokenResponse.parseIdToken();
            return idToken.getPayload();
        } catch (IOException e) {
            log.error("Error parsing Google ID Token: {}", e.getMessage());
            throw new IOException("Failed to parse Google ID Token", e);
        }
    }

    /**
     * Retrieves or creates a User entity based on the information in the GoogleIdToken's payload.
     *
     * @param payload The payload of the Google ID Token containing user information.
     * @return The retrieved or newly created User entity.
     */
    private User getUserFromPayload(GoogleIdToken.Payload payload) {
        return repository.findByEmail(payload.getEmail())
                .orElseGet(() -> createUserFromPayload(payload));
    }

    /**
     * Creates a new User entity from the Google ID Token's payload.
     *
     * @param payload The payload of the Google ID Token containing user information.
     * @return The newly created User entity.
     */
    private User createUserFromPayload(GoogleIdToken.Payload payload) {
        User newUser = User.builder()
                .firstname(payload.get("given_name").toString())
                .lastname(payload.get("family_name").toString())
                .email(payload.getEmail())
                .image(payload.get("picture").toString())
                .role(Role.USER)
                .password(passwordEncoder.encode("googlePassword"))
                .accountNonLocked(true)
                .enabled(true)
                .build();
        return repository.save(newUser);
    }

    /**
     * Generates and builds an AuthenticationResponse for the specified user.
     *
     * @param user The User entity for whom the authentication response is to be generated.
     * @return AuthenticationResponse containing access and refresh tokens.
     */
    private AuthenticationResponse createAuthenticationResponse(User user) {
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        userService.revokeAllUserTokens(user);
        userService.saveUserToken(user, jwtToken);
        log.info("Generated and saved tokens for user: {}", user.getEmail());
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }
}
