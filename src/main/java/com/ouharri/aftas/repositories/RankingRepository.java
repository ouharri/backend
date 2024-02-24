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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for accessing and managing {@link Ranking} entities in the database.
 */
@Repository
public interface RankingRepository extends JpaRepository<Ranking, RankingId> {

    /**
     * Calculates the total points for a member in a specific competition based on their hunting records.
     *
     * @param memberId      The ID of the member.
     * @param competitionId The ID of the competition.
     * @return The total points for the member in the competition.
     */
    @Query("SELECT SUM(f.level.points * h.numberOfFish) " +
            "FROM Hunting h " +
            "JOIN h.huntingCompositeKey.fish f " +
            "WHERE h.huntingCompositeKey.member.id = :memberId AND h.huntingCompositeKey.competition.id = :competitionId")
    Long calculateMemberPoints(@Param("memberId") UUID memberId, @Param("competitionId") UUID competitionId);

    /**
     * Retrieves the ranking for a specific member in a specific competition.
     *
     * @param member      The member entity.
     * @param competition The competition entity.
     * @return An optional containing the ranking for the specified member in the specified competition.
     */
    Optional<Ranking> findById_MemberAndIdCompetition(Member member, Competition competition);

    /**
     * Retrieves all rankings for a specific competition, ordered by score.
     *
     * @param competition The competition entity.
     * @return A list of rankings for the specified competition, ordered by score.
     */
    List<Ranking> findAllById_CompetitionOrderByScoreDesc(Competition competition);

    /**
     * Retrieves a paginated list of rankings for a specific competition.
     *
     * @param competition The competition entity.
     * @param pageable    The pagination information.
     * @return A paginated list of rankings for the specified competition.
     */
    Page<Ranking> getAllById_Competition(Competition competition, Pageable pageable);


}