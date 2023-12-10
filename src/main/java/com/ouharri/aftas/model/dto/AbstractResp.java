package com.ouharri.aftas.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link com.ouharri.aftas.model.entities.AbstractEntity}
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AbstractResp implements Serializable {
    UUID id;
    String createdAt;
    LocalDateTime updatedAt;
    Long version;
}