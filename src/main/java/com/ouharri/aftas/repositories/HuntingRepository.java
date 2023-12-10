package com.ouharri.aftas.repositories;

import com.ouharri.aftas.model.entities.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HuntingRepository extends JpaRepository<Hunting, UUID> {
}