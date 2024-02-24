package com.ouharri.aftas.model.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ouharri.aftas.model.entities.Fish;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

/**
 * DTO for {@link Fish}
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FishResponse extends AbstractResponse<UUID> {
    @NotBlank(message = "The name cannot be blank.")
    private String name;

    @NotNull(message = "The average weight cannot be null.")
    private Double averageWeight;

    private LevelResponse level;
}