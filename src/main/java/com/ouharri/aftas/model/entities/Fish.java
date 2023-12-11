package com.ouharri.aftas.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fish")
public class Fish extends AbstractEntity {
    @NotBlank(message = "The name cannot be blank.")
    private String name;

    @NotNull(message = "The average weight cannot be null.")
    @DecimalMin(value = "0.0", inclusive = false, message = "The average weight must be greater than 0.")
    private Double averageWeight;

    @ManyToOne
    @JoinColumn(name = "level_id", referencedColumnName = "id")
    private Level level;

    @OneToMany(mappedBy = "fish", cascade = CascadeType.ALL)
    private List<Hunting> huntings = new ArrayList<>();
}