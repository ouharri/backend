package com.ouharri.aftas.model.dto.Level;

import com.ouharri.aftas.model.entities.Level;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

/**
 * DTO for {@link Level}
 */
public record LevelReq(
        @NotBlank(message = "Name cannot be blank.") String name,
        @NotBlank(message = "Description cannot be blank.") String description,
        @NotNull(message = "Points cannot be null.") @Min(message = "The Points cannot be less than 0.", value = 0) @Positive Integer points
) implements Serializable {
}