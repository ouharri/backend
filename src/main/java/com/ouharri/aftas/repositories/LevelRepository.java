package com.ouharri.aftas.repositories;

import com.ouharri.aftas.model.entities.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for accessing and managing {@link Level} entities in the database.
 */
@Repository
public interface LevelRepository extends JpaRepository<Level, UUID> {

}