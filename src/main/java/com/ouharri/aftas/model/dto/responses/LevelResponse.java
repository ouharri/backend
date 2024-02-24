package com.ouharri.aftas.model.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ouharri.aftas.model.entities.Level;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

/**
 * DTO for {@link Level}
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LevelResponse extends AbstractResponse<UUID> {
    @NotBlank(message = "Name cannot be blank.")
    private String name;

    @NotBlank(message = "Description cannot be blank.")
    private String description;

    @NotNull(message = "Points cannot be null.")
    private Integer points;
}