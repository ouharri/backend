package com.ouharri.aftas.repositories;

import com.ouharri.aftas.model.entities.User;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for accessing and managing {@link User} entities in the database.
 */
@Repository
@NonNullApi
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Finds a user by their email address.
     *
     * @param email The email address of the user.
     * @return An optional containing the user, or empty if not found.
     */
    Optional<User> findByEmail(String email);

    /**
     * Finds a user by their ID.
     *
     * @param id The ID of the user.
     * @return An optional containing the user, or empty if not found.
     */
    Optional<User> findById(UUID id);
}
