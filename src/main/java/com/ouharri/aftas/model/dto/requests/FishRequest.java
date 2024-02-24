package com.ouharri.aftas.model.dto.requests;

import com.ouharri.aftas.model.dto.responses.LevelResponse;
import com.ouharri.aftas.model.entities.Fish;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for {@link Fish}
 */
public record FishRequest(
        @NotBlank(message = "The name cannot be blank.")
        String name,

        @NotNull(message = "The average weight cannot be null.")
        Double averageWeight,

        @NotNull
        LevelResponse level
) implements _Request {
}