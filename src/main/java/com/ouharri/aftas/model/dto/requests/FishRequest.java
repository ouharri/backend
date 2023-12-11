package com.ouharri.aftas.model.dto.requests;

import com.ouharri.aftas.model.dto.responces.LevelResp;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for {@link com.ouharri.aftas.model.entities.Fish}
 */
public record FishRequest(
        @NotBlank(message = "The name cannot be blank.")
        String name,

        @NotNull(message = "The average weight cannot be null.")
        Double averageWeight,

        @NotNull
        LevelResp level
) implements Request {
}