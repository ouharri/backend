package com.ouharri.aftas.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * Configuration class for Spring Security settings.
 * This class configures the security aspects of the application, including CORS settings,
 * CSRF protection, session management, authentication providers, and JWT filter integration.
 * It also defines the security filter chain that specifies which requests are secured and how.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">Ouharri Outman</a>
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private static final List<String> WHITE_LIST_URL = List.of(
            "/api/v2/**",
            "/api/v2/auth/**",
            "/api/v2/Oauth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/actuator/**"
    );
    private static final List<String> ALLOW_ORIGIN = List.of(
            "http://localhost:4200",
            "https://e044-197-230-250-154.ngrok-free.app"
    );
    private static final List<String> ALLOW_METHODS = List.of(
            "GET",
            "POST",
            "PUT",
            "DELETE",
            "OPTIONS"
    );
    private static final List<String> ALLOW_HEAD = List.of(
            "Access-Control-Allow-Origin",
            "Access-Control-Allow-Methods",
            "Access-Control-Allow-Headers",
            "Access-Control-Max-Age",
            "Access-Control-Request-Headers",
            "Access-Control-Request-Method",
            "accept",
            "authorization",
            "content-type",
            "X-CSRF-TOKEN",
            "x-xsrf-token",
            "user-agent",
            "x-requested-with",
            "ngrok-skip-browser-warning",
            "Origin",
            "Cache-Control",
            "Content-Type",
            "Authorization",
            "Accept",
            "X-Requested-With"
    );
    private final LogoutHandler logoutHandler;
    private final CsrfCookieFilter csrfCookieFilter;
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final SpaCsrfTokenRequestHandler spaCsrfTokenRequestHandler;

    /**
     * Configures the security filter chain for the application.
     * This method sets up CORS, CSRF protection, session management, and configures authentication
     * and authorization for HTTP requests. It also integrates the JWT authentication filter and
     * defines logout behavior.
     *
     * @param http HttpSecurity object to configure security settings.
     * @return SecurityFilterChain object representing the configured security filter chain.
     * @throws Exception if an error occurs during the configuration process.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(httpSecurityCorsConfigurer ->
                        httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource())
                )
                .csrf(csrf ->
                        csrf.csrfTokenRepository(csrfTokenRepository())
                                .csrfTokenRequestHandler(spaCsrfTokenRequestHandler)
                )
                .exceptionHandling(customizer ->
                        customizer.authenticationEntryPoint(
                                new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)
                        )
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(STATELESS)
                )
                .headers(headers -> headers
                        .httpStrictTransportSecurity(hsts -> hsts
                                .includeSubDomains(true)
                                .maxAgeInSeconds(31536000)
                        )
                )
                .authorizeHttpRequests(req ->
                        req.requestMatchers(createWhiteListMatchers())
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(
                        jwtAuthFilter,
                        UsernamePasswordAuthenticationFilter.class
                )
                .addFilterAfter(
                        csrfCookieFilter,
                        UsernamePasswordAuthenticationFilter.class
                )
                .logout(logout ->
                        logout.logoutUrl("/api/v2/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) ->
                                        response.setStatus(HttpStatus.OK.value())
                                )
                );
        return http.build();
    }

    /**
     * Creates and configures the CORS policy for the application.
     * This policy defines the allowed origins, HTTP methods, and headers for cross-origin requests.
     *
     * @return A CorsConfigurationSource object encapsulating the CORS configuration.
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(ALLOW_ORIGIN);
        configuration.setAllowedMethods(ALLOW_METHODS);
        configuration.setAllowedHeaders(ALLOW_HEAD);
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Creates and configures the CSRF token repository.
     * This repository is responsible for storing and managing CSRF tokens,
     * using cookies as the storage mechanism.
     *
     * @return A CsrfTokenRepository object for managing CSRF tokens.
     */
    @Bean
    public CsrfTokenRepository csrfTokenRepository() {
        CookieCsrfTokenRepository repository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        repository.setCookiePath("/");
        return repository;
    }

    /**
     * Generates an array of AntPathRequestMatcher objects based on a list of URL patterns.
     * These matchers are used to define which paths are exempt from authentication.
     *
     * @return An array of AntPathRequestMatcher objects.
     */
    private AntPathRequestMatcher[] createWhiteListMatchers() {
        return WHITE_LIST_URL.stream()
                .map(AntPathRequestMatcher::new)
                .toArray(AntPathRequestMatcher[]::new);
    }
}