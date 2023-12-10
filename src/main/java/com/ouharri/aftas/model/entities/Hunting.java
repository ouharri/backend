package com.ouharri.aftas.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hunting")
public class Hunting extends AbstractEntity {
    @NotNull(message = "The number of fish cannot be null.")
    @Positive(message = "The number of fish must be a positive integer.")
    private Integer numberOfFish;

    @ManyToOne
    @JoinColumn(name = "fish_id", referencedColumnName = "id")
    private Fish fish;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "competition_id", referencedColumnName = "id")
    private Competition competition;
}