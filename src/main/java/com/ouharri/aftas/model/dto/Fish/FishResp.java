package com.ouharri.aftas.model.dto.Fish;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ouharri.aftas.model.dto.AbstractResp;
import com.ouharri.aftas.model.dto.Level.LevelResp;
import com.ouharri.aftas.model.entities.Fish;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * DTO for {@link Fish}
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FishResp extends AbstractResp {

    @NotBlank(message = "The name cannot be blank.")
    private String name;

    @NotNull(message = "The average weight cannot be null.")
    private Double averageWeight;

    private LevelResp level;

}