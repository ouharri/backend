package com.ouharri.aftas.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a type of fish that can be hunted in competitions.
 * Extends the AbstractEntity class.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">Ouharri Outman</a>
 * @version 2.0
 */
@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fish")
public class Fish extends AbstractEntity<UUID> {

    /**
     * The name of the fish.
     */
    @NotBlank(message = "The name cannot be blank.")
    private String name;

    /**
     * The average weight of the fish.
     */
    @NotNull(message = "The average weight cannot be null.")
    @DecimalMin(value = "0.0", inclusive = false, message = "The average weight must be greater than 0.")
    private Double averageWeight;

    /**
     * The level of difficulty associated with hunting this fish.
     */
    @ManyToOne
    @JoinColumn(
            name = "level_id",
            referencedColumnName = "id"
    )
    private Level level;

    /**
     * List of hunting records associated with this fish.
     */
    @OneToMany(
            mappedBy = "huntingCompositeKey.fish",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<Hunting> huntings = new ArrayList<>();
}