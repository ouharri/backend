package com.ouharri.aftas.model.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;

/**
 * Represents the composite key for the Ranking entity, consisting of the associated Competition and Member.
 * Implements Serializable and Embeddable for use as an embedded key in the Ranking entity.
 *
 * @author ouharri
 * @version 2.0
 */
@Getter
@Setter
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RankingId implements Serializable {

    /**
     * The competition associated with the ranking.
     */
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            targetEntity = Competition.class
    )
    private Competition competition;

    /**
     * The member associated with the ranking.
     */
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            targetEntity = Member.class
    )
    private Member member;
}
