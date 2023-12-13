package com.ouharri.aftas.repositories;

import com.ouharri.aftas.model.entities.Competition;
import com.ouharri.aftas.model.entities.Member;
import com.ouharri.aftas.model.entities.Ranking;
import com.ouharri.aftas.model.entities.RankingId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, RankingId> {

    @Query("SELECT RANK() OVER (ORDER BY r.score DESC) as position, " +
            "(SELECT SUM(f.level.points * h.numberOfFish) " +
            " FROM Hunting h " +
            " JOIN h.huntingCompositeKey.fish f " +
            " WHERE h.huntingCompositeKey.competition.id = :competitionId " +
            " AND h.huntingCompositeKey.member.id = :memberId) as totalPoints " +
            "FROM Ranking r " +
            "WHERE r.id.competition.id = :competitionId " +
            "AND r.id.member.id = :memberId")
    Object[] getMemberRankingAndTotalPointsInCompetition(@Param("competitionId") UUID competitionId,
                                                         @Param("memberId") UUID memberId);

    @Query("SELECT SUM(f.level.points * h.numberOfFish) " +
            "FROM Hunting h " +
            "JOIN h.huntingCompositeKey.fish f " +
            "WHERE h.huntingCompositeKey.member.id = :memberId AND h.huntingCompositeKey.competition.id = :competitionId")
    Long calculateMemberPoints(@Param("memberId") UUID memberId, @Param("competitionId") UUID competitionId);

    Optional<Ranking> findById_MemberAndIdCompetition(Member member, Competition competition);

    Page<Ranking> getAllById_Competition(Competition competition, Pageable pageable);

}