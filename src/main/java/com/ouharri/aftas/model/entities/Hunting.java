package com.ouharri.aftas.model.entities;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

/**
 * Represents a record of a member's hunting activity in a competition.
 * Extends the AbstractEntity class.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">Ouharri Outman</a>
 * @version 2.0
 */
@Entity
@Getter
@Setter
@SuperBuilder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "hunting",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {
                        "fish_id",
                        "member_id",
                        "competition_id"
                }
        )
)
public class Hunting extends AbstractEntity<UUID> {

    /**
     * The number of fish caught during the hunting activity.
     */
    @NotNull(message = "The number of fish cannot be null.")
    @Positive(message = "The number of fish must be a positive integer.")
    private int numberOfFish = 0;

    @Embedded
    private HuntingCompositeKey huntingCompositeKey;
}