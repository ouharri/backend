package com.ouharri.aftas.core;

import com.ouharri.aftas.repositories.UserRepository;
import com.ouharri.aftas.security.GoogleOpaqueTokenIntrospector;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

/**
 * Configuration class for application-related beans and settings.
 * This class includes configurations for user details service, authentication provider,
 * auditor awareness, authentication manager, password encoder, model mapper, and opaque token introspector.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">ouharri outman</a>
 */
@Configuration
@RequiredArgsConstructor
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class ApplicationConfig {

    private final WebClient userInfoClient;
    private final UserRepository repository;

    /**
     * Creates a custom implementation of UserDetailsService to load user details by email.
     *
     * @return UserDetailsService implementation
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    /**
     * Configures and provides a custom AuthenticationProvider using DaoAuthenticationProvider.
     *
     * @return AuthenticationProvider
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Creates an instance of AuditorAware to provide the current user's ID for entity auditing.
     *
     * @return AuditorAware implementation
     */
    @Bean
    public AuditorAware<UUID> auditorAware() {
        return new ApplicationAuditAware();
    }

    /**
     * Retrieves the AuthenticationManager from the provided AuthenticationConfiguration.
     *
     * @param config AuthenticationConfiguration
     * @return AuthenticationManager
     * @throws Exception if an error occurs while retrieving the AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Creates an instance of BCryptPasswordEncoder as the password encoder.
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Creates an instance of ModelMapper to map entities to DTOs.
     *
     * @return ModelMapper
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    /**
     * Creates and configures an OpaqueTokenIntrospector bean, specifically a custom
     * implementation for Google's opaque token introspection.
     * This is used in the security configuration to validate and introspect OAuth2 tokens.
     *
     * @return An instance of OpaqueTokenIntrospector.
     */
    @Bean
    public OpaqueTokenIntrospector introspector() {
        return new GoogleOpaqueTokenIntrospector(userInfoClient);
    }

}