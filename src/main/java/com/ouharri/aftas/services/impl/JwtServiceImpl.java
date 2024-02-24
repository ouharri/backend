package com.ouharri.aftas.services.impl;

import com.ouharri.aftas.repositories.TokenRepository;
import com.ouharri.aftas.services.spec.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Service class for handling JWT generation, parsing, and validation.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">Ouharri Outman</a>
 */
@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final TokenRepository tokenRepository;
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    /**
     * Extracts the username from a JWT token.
     *
     * @param token JWT token
     * @return Username extracted from the token
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts a specific claim from a JWT token using the provided claims' resolver.
     *
     * @param token          JWT token
     * @param claimsResolver Function to resolve the desired claim from the token's claims
     * @param <T>            Type of the claim
     * @return Extracted claim value
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Generates a JWT token for the given user details with additional claims.
     *
     * @param extraClaims Additional claims to include in the token
     * @param userDetails User details for whom the token is generated
     * @return Generated JWT token
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return generateToken(extraClaims, userDetails, jwtExpiration);
    }

    /**
     * Generates a refresh token for the given user details.
     *
     * @param userDetails User details for whom the refresh token is generated
     * @return Generated refresh token
     */
    public String generateRefreshToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails, refreshExpiration);
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
        return buildToken(extraClaims, userDetails, expiration);
    }

    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .filter(authority -> authority.startsWith("ROLE_"))
                .collect(Collectors.toList());

        List<String> permissions = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .filter(authority -> !authority.startsWith("ROLE_"))
                .collect(Collectors.toList());
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .claim("roles", roles)
                .claim("permissions", permissions)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Validates whether a JWT token is valid for the given user details.
     *
     * @param token       JWT token to be validated
     * @param userDetails User details to validate against
     * @return True if the token is valid, false otherwise
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        var isTokenValid = tokenRepository.findByToken(token)
                .map(t -> !t.isExpired() && !t.isRevoked())
                .orElse(false);

        return (username.equals(userDetails.getUsername()))
                && !isTokenExpired(token)
                && isTokenValid;
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}