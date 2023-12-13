package com.ouharri.aftas.model.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;

/**
 * Composite key representing the unique combination of fish, member, and competition in hunting activity.
 */
@Getter
@Setter
@Builder
@Embeddable
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class HuntingCompositeKey implements Serializable {

    /**
     * The type of fish hunted.
     */
    @ManyToOne
    @JoinColumn(
            name = "fish_id",
            referencedColumnName = "id"
    )
    private Fish fish;

    /**
     * The member who participated in the hunting activity.
     */
    @ManyToOne
    @JoinColumn(
            name = "member_id",
            referencedColumnName = "id"
    )
    private Member member;

    /**
     * The competition in which the hunting activity took place.
     */
    @ManyToOne
    @JoinColumn(
            name = "competition_id",
            referencedColumnName = "id"
    )
    private Competition competition;
}
