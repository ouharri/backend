package com.ouharri.aftas.model.dto.Fish;

import com.ouharri.aftas.model.dto.Level.LevelDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.ouharri.aftas.model.entities.Fish}
 */
public record FishDto(
        @NotBlank(message = "The name cannot be blank.") String name,
        @NotNull(message = "The average weight cannot be null.") Double averageWeight,
        @NotNull LevelDto level
) implements Serializable {
}