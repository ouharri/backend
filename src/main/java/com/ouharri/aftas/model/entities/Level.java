package com.ouharri.aftas.model.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
 * Represents a level of difficulty associated with fishing.
 * Extends the AbstractEntity class.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">Ouharri Outman</a>
 * @version 2.0
 */
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "level")
public class Level extends AbstractEntity<UUID> {

    /**
     * The name of the fishing level.
     */
    @NotBlank(message = "Name cannot be blank.")
    private String name;

    /**
     * The description of the fishing level.
     */
    @NotBlank(message = "Description cannot be blank.")
    private String description;

    /**
     * The points associated with this fishing level.
     */
    @NotNull(message = "Points cannot be null.")
    private Integer points;

    /**
     * The list of fishes associated with this fishing level.
     */
    @OneToMany(mappedBy = "level", cascade = CascadeType.ALL)
    private List<Fish> fishes = new ArrayList<>();
}