package com.ouharri.aftas.model.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Base class for response DTOs providing audit information.
 * This class includes fields for tracking the creation and last update timestamps,
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">Ouharri Outman</a>
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AuditableResponse implements Serializable {
    /**
     * Serial version UID for serialization.
     */
    @Serial
    private static final long serialVersionUID = 3L;

    /**
     * The timestamp when the entity was created.
     */
    private Timestamp createdAt;

    /**
     * The timestamp when the entity was last updated.
     */
    private Timestamp updatedAt;

    /**
     * The version number of the entity.
     */
    private Long version;
}
