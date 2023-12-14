package com.ouharri.aftas.services.spec;

import com.ouharri.aftas.model.dto.requests.ChangePasswordRequest;
import com.ouharri.aftas.model.entities.User;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service interface for managing User entities.
 */
public interface UserService {

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id The unique identifier of the user.
     * @return An Optional containing the user, or an empty Optional if not found.
     */
    Optional<User> findById(UUID id);

    /**
     * Retrieves a list of all users.
     *
     * @return A list of all users.
     */
    List<User> getAllUsers();

    /**
     * Changes the password for the user identified by the connected user principal.
     *
     * @param request       The request containing the old and new passwords.
     * @param connectedUser The principal representing the connected user.
     */
    void changePassword(ChangePasswordRequest request, Principal connectedUser);
}
