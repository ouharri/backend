package com.ouharri.aftas.model.dto.Hunting;

import com.ouharri.aftas.model.dto.Competition.CompetitionDto;
import com.ouharri.aftas.model.dto.Fish.FishDto;
import com.ouharri.aftas.model.dto.Member.MemberDto;
import com.ouharri.aftas.model.entities.Hunting;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

/**
 * DTO for {@link Hunting}
 */
public record HuntingDto(
        @NotNull(message = "The number of fish cannot be null.") @Positive(message = "The number of fish must be a positive integer.") Integer numberOfFish,
        @NotNull(message = "The fish cannot be null.") FishDto fish,
        @NotNull(message = "The member cannot be null.") MemberDto member,
        @NotNull(message = "The competition cannot be null.") CompetitionDto competition) implements Serializable {
}