package com.ouharri.aftas.services.spec;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

/**
 * Interface for JWT service operations.
 * This interface defines methods for generating, parsing, and validating JWT tokens.
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 */
public interface JwtService {
    /**
     * Extracts the username from a given JWT token.
     *
     * @param token JWT token from which to extract the username.
     * @return The username extracted from the token.
     */
    String extractUsername(String token);

    /**
     * Extracts a specific claim from a JWT token.
     *
     * @param <T>            The type of the extracted claim.
     * @param token          JWT token from which to extract the claim.
     * @param claimsResolver Function to apply to the token's claims.
     * @return The extracted claim.
     */
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    /**
     * Generates a JWT token based on the user's details.
     *
     * @param userDetails The user details for which to generate the token.
     * @return The generated JWT token.
     */
    String generateToken(UserDetails userDetails);

    /**
     * Generates a JWT token with additional claims.
     *
     * @param extraClaims Additional claims to be included in the token.
     * @param userDetails The user details for which to generate the token.
     * @return The generated JWT token.
     */
    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

    /**
     * Generates a refresh token for a user.
     *
     * @param userDetails The user details for which to generate the refresh token.
     * @return The generated refresh token.
     */
    String generateRefreshToken(UserDetails userDetails);

    /**
     * Validates a JWT token against user details.
     *
     * @param token       The JWT token to validate.
     * @param userDetails The user details against which to validate the token.
     * @return True if the token is valid, otherwise false.
     */
    boolean isTokenValid(String token, UserDetails userDetails);

    /**
     * Checks if a JWT token is expired.
     *
     * @param token The JWT token to check.
     * @return True if the token is expired, otherwise false.
     */
    boolean isTokenExpired(String token);
}
