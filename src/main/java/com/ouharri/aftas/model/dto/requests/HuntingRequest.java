package com.ouharri.aftas.model.dto.requests;

import com.ouharri.aftas.model.dto.responses.CompetitionResponse;
import com.ouharri.aftas.model.dto.responses.FishResponse;
import com.ouharri.aftas.model.dto.responses.MemberResponse;
import com.ouharri.aftas.model.entities.Hunting;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * DTO for {@link Hunting}
 */
public record HuntingRequest(
        @NotNull(message = "The number of fish cannot be null.")
        @Positive(message = "The number of fish must be a positive integer.")
        Integer numberOfFish,

        @NotNull(message = "The fish cannot be null.")
        FishResponse fish,

        @NotNull(message = "The member cannot be null.")
        MemberResponse member,

        @NotNull(message = "The competition cannot be null.")
        CompetitionResponse competition
) implements _Request {
}