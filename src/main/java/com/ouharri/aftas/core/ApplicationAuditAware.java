package com.ouharri.aftas.core;

import com.ouharri.aftas.model.entities.User;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

/**
 * Custom implementation of Spring Data's AuditorAware interface to provide the current user's ID
 * for entity auditing in the application.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">Ouharri Outman</a>
 */
@NonNullApi
public class ApplicationAuditAware implements AuditorAware<UUID> {

    /**
     * Retrieve the ID of the currently authenticated user as the auditor for entity auditing.
     *
     * @return Optional containing the ID of the current user, or empty if no user is authenticated or anonymous.
     */
    @Override
    public Optional<UUID> getCurrentAuditor() {
        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();
        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken
        ) {
            return Optional.empty();
        }

        User userPrincipal = (User) authentication.getPrincipal();
        return Optional.of(userPrincipal.getId());
    }
}