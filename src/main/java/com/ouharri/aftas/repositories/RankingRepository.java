package com.ouharri.aftas.repositories;

import com.ouharri.aftas.model.entities.Ranking;
import com.ouharri.aftas.model.entities.RankingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, RankingId> {
}