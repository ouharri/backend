package com.ouharri.aftas.repositories;

import com.ouharri.aftas.model.entities.Hunting;
import com.ouharri.aftas.model.entities.HuntingCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for accessing and managing {@link Hunting} entities in the database.
 */
@Repository
public interface HuntingRepository extends JpaRepository<Hunting, UUID> {

    /**
     * Checks if a hunting entry with the specified composite key exists.
     *
     * @param huntingCompositeKey The composite key to check.
     * @return true if the hunting entry exists, false otherwise.
     */
    Boolean existsByHuntingCompositeKey(HuntingCompositeKey huntingCompositeKey);

    /**
     * Calculates the total points for a member in a specific competition based on their hunting entries.
     *
     * @param memberId      The ID of the member.
     * @param competitionId The ID of the competition.
     * @return The total points for the member in the specified competition.
     */
    @Query("SELECT SUM(f.level.points * h.numberOfFish) " +
            "FROM Hunting h " +
            "JOIN h.huntingCompositeKey.fish f " +
            "WHERE h.huntingCompositeKey.member.id = :memberId AND h.huntingCompositeKey.competition.id = :competitionId")
    Long calculateMemberPoints(@Param("memberId") UUID memberId, @Param("competitionId") UUID competitionId);
}