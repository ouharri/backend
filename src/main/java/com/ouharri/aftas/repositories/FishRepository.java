package com.ouharri.aftas.repositories;

import com.ouharri.aftas.model.entities.Fish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FishRepository extends JpaRepository<Fish, UUID> {
}