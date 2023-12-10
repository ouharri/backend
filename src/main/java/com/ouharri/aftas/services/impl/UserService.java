package com.ouharri.aftas.services.impl;

import com.ouharri.aftas.model.dto.auth.ChangePasswordRequest;
import com.ouharri.aftas.model.entities.User;
import com.ouharri.aftas.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class for managing user-related operations.
 *
 * @author Ouharri Outman
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;

    /**
     * Retrieves a list of all users.
     *
     * @return List of all users.
     */
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    /**
     * Retrieves a user by email.
     *
     * @param email The email of the user to retrieve.
     * @return Optional containing the user if found, otherwise empty.
     */
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    /**
     * Retrieves a user by ID.
     *
     * @param username The ID of the user to retrieve.
     * @return Optional containing the user if found, otherwise empty.
     */
    public Optional<User> findById(UUID username) {
        return repository.findById(username);
    }

    /**
     * Changes the password of the currently logged-in user.
     *
     * @param request        The change password request.
     * @param connectedUser  The principal representing the currently connected user.
     * @throws IllegalStateException If the current password is incorrect, or if the new passwords do not match.
     */
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }

        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Passwords are not the same");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        repository.save(user);
    }
}