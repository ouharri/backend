package com.ouharri.aftas.model.dto.Hunting;

import com.ouharri.aftas.model.dto.Competition.CompetitionReq;
import com.ouharri.aftas.model.dto.Fish.FishReq;
import com.ouharri.aftas.model.dto.Member.MemberReq;
import com.ouharri.aftas.model.entities.Hunting;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

/**
 * DTO for {@link Hunting}
 */
public record HuntingReq(
        @NotNull(message = "The number of fish cannot be null.") @Positive(message = "The number of fish must be a positive integer.") Integer numberOfFish,
        @NotNull(message = "The fish cannot be null.") FishReq fish,
        @NotNull(message = "The member cannot be null.") MemberReq member,
        @NotNull(message = "The competition cannot be null.") CompetitionReq competition) implements Serializable {
}