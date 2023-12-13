package com.ouharri.aftas.repositories;

import com.ouharri.aftas.model.entities.Hunting;
import com.ouharri.aftas.model.entities.HuntingCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting, UUID> {
    Boolean existsByHuntingCompositeKey(HuntingCompositeKey huntingCompositeKey);

    @Query("SELECT SUM(f.level.points * h.numberOfFish) " +
            "FROM Hunting h " +
            "JOIN h.huntingCompositeKey.fish f " +
            "WHERE h.huntingCompositeKey.member.id = :memberId AND h.huntingCompositeKey.competition.id = :competitionId")
    Long calculateMemberPoints(@Param("memberId") UUID memberId, @Param("competitionId") UUID competitionId);
}