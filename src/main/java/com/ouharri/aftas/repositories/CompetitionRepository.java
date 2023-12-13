package com.ouharri.aftas.repositories;

import com.ouharri.aftas.model.entities.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, UUID> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Competition c WHERE c.id = :id AND c.endTime < CURRENT_TIME")
    boolean hasCompetitionEnded(@Param("id") UUID id);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Competition c WHERE c.id = :id AND CURRENT_DATE > c.date OR (CURRENT_DATE = c.date AND CURRENT_TIME > c.startTime)")
    boolean hasCompetitionStarted(@Param("id") UUID id);
}