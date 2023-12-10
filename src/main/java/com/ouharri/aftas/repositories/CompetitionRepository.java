package com.ouharri.aftas.repositories;

import com.ouharri.aftas.model.entities.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, UUID> {
}