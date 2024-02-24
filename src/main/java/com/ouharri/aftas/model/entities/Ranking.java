package com.ouharri.aftas.model.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

/**
 * Represents the ranking of a member in a competition.
 * Implements the _Entity interface with a composite key of type RankingId.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">Ouharri Outman</a>
 * @version 2.0
 */
@Getter
@Setter
@Builder
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ranking")
public class Ranking extends AuditableEntity implements _Entity<RankingId> {

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
    @ColumnDefault("-1")
    @Positive(message = "Rank must be positive.")
    private Integer rank;
}