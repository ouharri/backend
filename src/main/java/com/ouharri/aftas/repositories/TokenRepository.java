package com.ouharri.aftas.repositories;

import com.ouharri.aftas.model.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for accessing and managing {@link Token} entities in the database.
 */
@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

    /**
     * Finds all valid tokens associated with a user based on the user's ID.
     *
     * @param id The ID of the user.
     * @return A list of valid tokens associated with the user.
     */
    @Query(value = """
            SELECT t FROM Token t INNER JOIN User u
            ON t.user.id = u.id
            WHERE u.id = :id AND (t.expired = false OR t.revoked = false)
            """)
    List<Token> findAllValidTokenByUser(UUID id);

    /**
     * Finds a token by its token string.
     *
     * @param token The token string.
     * @return An optional containing the token, or empty if not found.
     */
    Optional<Token> findByToken(String token);
}