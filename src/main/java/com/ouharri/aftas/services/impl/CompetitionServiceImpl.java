package com.ouharri.aftas.services.impl;

import com.ouharri.aftas.exceptions.ResourceNotCreatedException;
import com.ouharri.aftas.job.RankingJob;
import com.ouharri.aftas.model.dto.requests.CompetitionRequest;
import com.ouharri.aftas.model.dto.responses.CompetitionResponse;
import com.ouharri.aftas.model.entities.Competition;
import com.ouharri.aftas.mapper.CompetitionMapper;
import com.ouharri.aftas.repositories.CompetitionRepository;
import com.ouharri.aftas.services.spec.CompetitionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of the {@link CompetitionService} interface.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CompetitionServiceImpl extends _ServiceImp<UUID, CompetitionRequest, CompetitionResponse, Competition, CompetitionRepository, CompetitionMapper> implements CompetitionService {

    private final Scheduler scheduler;

    /**
     * Creates a new competition based on the provided request DTO.
     *
     * @param request DTO containing data for entity creation.
     * @return Optional containing the response DTO of the created entity.
     * @throws ResourceNotCreatedException If an error occurs during entity creation.
     */
    @Override
    public Optional<CompetitionResponse> create(CompetitionRequest request) {
        Competition entityToCreate = mapper.toEntityFromRequest(request);
        try {
            assert repository != null;
            Competition createdEntity = repository.saveAndFlush(entityToCreate);
            startTriggerJob(createdEntity);
            return Optional.of(mapper.toResponse(createdEntity));
        } catch (Exception e) {
            log.error("Error while creating entity", e);
            throw new ResourceNotCreatedException(e.getMessage());
        }
    }

    /**
     * Retrieves a competition by its unique identifier.
     *
     * @param id The unique identifier of the competition.
     * @return Optional containing the competition, or an empty Optional if not found.
     */
    public Optional<Competition> getCompetitionById(UUID id) {
        return repository.findById(id);
    }

    /**
     * Starts a Quartz job to calculate rankings for the specified competition.
     *
     * @param competition The competition for which to calculate rankings.
     */
    public void startTriggerJob(Competition competition) {
        LocalDate competitionDate = competition.getDate().toLocalDate();
        LocalTime competitionEndTime = competition.getEndTime().toLocalTime();

        LocalDateTime competitionEndDateTime =
                LocalDateTime.of(
                        competitionDate,
                        competitionEndTime
                );

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("jobID", "Job-" + competition.getId());
        jobDataMap.put("competitionID", competition.getId());

        JobDetail job = JobBuilder.newJob(RankingJob.class)
                .withIdentity("jobIdentity-" + competition.getId())
                .usingJobData(jobDataMap)
                .build();

        Trigger trigger =
                TriggerBuilder.newTrigger()
                        .withIdentity("triggerIdentity-" + competition.getId())
                        .startAt(
                                Date.from(
                                        competitionEndDateTime.atZone(
                                                ZoneId.systemDefault()
                                        ).toInstant()
                                )
                        )
                        .build();

        try {
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
}