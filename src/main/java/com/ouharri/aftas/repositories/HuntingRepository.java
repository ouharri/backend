package com.ouharri.aftas.repositories;

import com.ouharri.aftas.model.entities.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting, UUID> {
}