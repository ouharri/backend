package com.ouharri.aftas.job;

import com.ouharri.aftas.model.entities.Competition;
import com.ouharri.aftas.services.spec.CompetitionService;
import com.ouharri.aftas.services.spec.RankingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;


/**
 * Quartz Job for calculating rankings for a competition when the ends.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RankingJob implements Job {
    private final RankingService rankingService;
    private final CompetitionService competitionService;


    /**
     * Executes the ranking calculation job.
     *
     * @param jobExecutionContext The execution context of the Quartz job.
     * @throws JobExecutionException If an error occurs during job execution.
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
            Competition
                    competition = competitionService.getCompetitionById(
                            (UUID) jobDataMap.get("competitionID")
                    )
                    .orElseThrow(
                            () -> new RuntimeException("Competition not found")
                    );

            rankingService.calculateRanking(competition);
            String jobId = (String) jobDataMap.get("jobID");
            log.info("Job Started-" + jobId + " at:" + new Date() + " for competition:" + competition.getId());
        } catch (Exception e) {
            log.error("Error while executing job", e);
            throw new JobExecutionException(e.getMessage());
        }
    }
}
