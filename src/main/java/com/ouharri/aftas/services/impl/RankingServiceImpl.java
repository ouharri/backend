package com.ouharri.aftas.services.impl;

import com.ouharri.aftas.model.dto.requests.RankingRequest;
import com.ouharri.aftas.model.dto.responses.CompetitionResponse;
import com.ouharri.aftas.model.dto.responses.RankingResponse;
import com.ouharri.aftas.model.entities.Competition;
import com.ouharri.aftas.model.entities.Hunting;
import com.ouharri.aftas.model.entities.Ranking;
import com.ouharri.aftas.model.entities.RankingId;
import com.ouharri.aftas.mapper.CompetitionMapper;
import com.ouharri.aftas.mapper.RankingMapper;
import com.ouharri.aftas.repositories.RankingRepository;
import com.ouharri.aftas.services.spec.RankingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * Implementation of the {@link RankingService} interface.
 * @author <a href="mailto:ouharrioutman@gmail.com">Ouharri Outman</a>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RankingServiceImpl extends _ServiceImp<RankingId, RankingRequest, RankingResponse, Ranking, RankingRepository, RankingMapper> implements RankingService {

    private final CompetitionMapper competitionMapper;

    /**
     * Calculates and sets the ranking based on the provided hunting data.
     *
     * @param hunting The hunting data used for ranking calculation.
     */
    @Transactional
    public void calculateAndSetRanking(Hunting hunting) {
        Ranking ranking;
        Optional<Ranking> rankingTmp =
                repository.findById_MemberAndIdCompetition(hunting.getHuntingCompositeKey().getMember(), hunting.getHuntingCompositeKey().getCompetition());

        if (rankingTmp.isPresent())
            ranking = rankingTmp.get();
        else {
            ranking = new Ranking();
            RankingId rankingId = new RankingId();
            rankingId.setMember(hunting.getHuntingCompositeKey().getMember());
            rankingId.setCompetition(hunting.getHuntingCompositeKey().getCompetition());
            ranking.setId(rankingId);
        }
        
        ranking.setScore(
                repository.calculateMemberPoints(
                        hunting.getHuntingCompositeKey().getMember().getId(),
                        hunting.getHuntingCompositeKey().getCompetition().getId()
                )
        );
        repository.saveAndFlush(ranking);
        calculateRanking(hunting.getHuntingCompositeKey().getCompetition());
    }

    /**
     * Retrieves all rankings for a given competition and returns them as paginated responses.
     *
     * @param competition The competition for which rankings are retrieved.
     * @param pageable    The pagination information.
     * @return Page of {@link RankingResponse} containing the rankings for the specified competition.
     */
    public Page<RankingResponse> getAllByCompetition(
            CompetitionResponse competition,
            Pageable pageable
    ) {
        Competition competitionEntity = competitionMapper.toEntityFromResponse(competition);
        return repository
                .getAllById_Competition(competitionEntity, pageable)
                .map(mapper::toResponse);
    }

    /**
     * Calculates and updates the ranking for a given competition.
     *
     * @param competition The competition for which rankings are calculated.
     */
    public void calculateRanking(Competition competition) {
        List<Ranking> rankings = repository
                .findAllById_CompetitionOrderByScoreDesc(competition);

        IntStream.range(0, rankings.size())
                .forEach(index ->
                        rankings.get(index).setRank(index + 1)
                );

        repository.saveAllAndFlush(rankings);
    }

}