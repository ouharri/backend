package com.ouharri.aftas.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.sql.Timestamp;

/**
 * Represents the ranking of a member in a competition.
 * Implements the _Entity interface with a composite key of type RankingId.
 *
 * @author ouharri
 * @version 2.0
 */
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ranking")
public class Ranking implements _Entity<RankingId> {

    /**
     * Serial version UID for serialization.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * The composite key for the ranking, consisting of competition, and member IDs.
     */
    @EmbeddedId
    private RankingId id;

    /**
     * The score achieved by the member in the competition.
     */
    @NotNull(message = "Score cannot be null.")
    private long score;

    /**
     * The rank of the member in the competition.
     */
    @Positive(message = "Rank must be positive.")
    private Integer rank = 0;

    /**
     * The timestamp indicating the creation time of the ranking.
     */
    @CreationTimestamp
    @ReadOnlyProperty
    @Builder.Default
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    /**
     * The timestamp indicating the last update time of the ranking.
     */
    @UpdateTimestamp
    @ReadOnlyProperty
    @LastModifiedDate
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updatedAt = new Timestamp(System.currentTimeMillis());

    /**
     * The version number for optimistic locking.
     */
    @Version
    @ReadOnlyProperty
    private Long version = 0L;
}