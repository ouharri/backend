package com.ouharri.aftas.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Represents a record of a member's hunting activity in a competition.
 * Extends the AbstractEntity class.
 *
 * @author ouharri
 * @version 2.0
 */
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hunting")
public class Hunting extends AbstractEntity {

    /**
     * The number of fish caught during the hunting activity.
     */
    @NotNull(message = "The number of fish cannot be null.")
    @Positive(message = "The number of fish must be a positive integer.")
    private int numberOfFish;

    /**
     * The type of fish hunted.
     */
    @ManyToOne
    @JoinColumn(name = "fish_id", referencedColumnName = "id")
    private Fish fish;

    /**
     * The member who participated in the hunting activity.
     */
    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

    /**
     * The competition in which the hunting activity took place.
     */
    @ManyToOne
    @JoinColumn(name = "competition_id", referencedColumnName = "id")
    private Competition competition;
}