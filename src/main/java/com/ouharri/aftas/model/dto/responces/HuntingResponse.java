package com.ouharri.aftas.model.dto.responces;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link com.ouharri.aftas.model.entities.Hunting}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record HuntingResponse(
        UUID id,
        String createdAt,
        LocalDateTime updatedAt,
        @NotNull(message = "The number of fish cannot be null.")
        @Positive(message = "The number of fish must be a positive integer.")
        Integer numberOfFish,
        FishResponse fish,
        MemberResponse member,
        CompetitionResponse competition
) implements Response {
}