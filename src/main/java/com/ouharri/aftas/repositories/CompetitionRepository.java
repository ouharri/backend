package com.ouharri.aftas.repositories;

import com.ouharri.aftas.model.entities.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for accessing and managing {@link Competition} entities in the database.
 */
@Repository
public interface CompetitionRepository extends JpaRepository<Competition, UUID> {

    /**
     * Checks if a competition with the specified ID has not started yet.
     *
     * @param id The ID of the competition to check.
     * @return true if the competition has not started, false otherwise.
     */
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Competition c WHERE c.id = :id AND (CURRENT_DATE < c.date AND CURRENT_TIME < c.startTime)")
    boolean hasCompetitionNotStarted(@Param("id") UUID id);

    /**
     * Checks if a competition with the specified ID has ended.
     *
     * @param id The ID of the competition to check.
     * @return true if the competition has ended, false otherwise.
     */
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Competition c WHERE c.id = :id AND (CURRENT_DATE > c.date or (CURRENT_DATE = c.date AND CURRENT_TIME > c.endTime))")
    boolean hasCompetitionEnded(@Param("id") UUID id);
}
