package com.ouharri.aftas.repositories;

import com.ouharri.aftas.model.entities.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LevelRepository extends JpaRepository<Level, UUID> {
}