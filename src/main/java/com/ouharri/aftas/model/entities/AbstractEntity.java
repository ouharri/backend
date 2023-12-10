package com.ouharri.aftas.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Abstract base class for entities in the Aftas application.
 * Provides common fields such as id, creation timestamp, and update timestamp.
 * Subclasses should use the @MappedSuperclass annotation.
 *
 * @author Ouharri Outman
 * @version 1.0
 */
@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractEntity implements Serializable {

    /**
     * The unique identifier for the entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * The timestamp indicating when the entity was created.
     */
    @CreationTimestamp
    @Column(name = "created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected String createdAt =
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    /**
     * The timestamp indicating when the entity was last updated.
     */
    @UpdateTimestamp
    @Column(name = "updated_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime updatedAt;

    /**
     * The version of the entity, used for optimistic locking.
     */
    @Version
    private Long version = 0L;

}