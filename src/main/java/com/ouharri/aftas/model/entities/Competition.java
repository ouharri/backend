package com.ouharri.aftas.model.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "competition")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Competition extends AbstractEntity {
    @Pattern(regexp = "^[a-zA-Z]{3}-\\d{2}-\\d{2}-\\d{2}$", message = "The code must follow the specified format.")
    private String code;

    @NotNull(message = "The date cannot be null.")
    @Temporal(TemporalType.DATE)
    private Date date;

    @NotNull(message = "The start time cannot be null.")
    @Temporal(TemporalType.TIME)
    private Time startTime;

    @NotNull(message = "The end time cannot be null.")
    @Temporal(TemporalType.TIME)
    private Time endTime;

    @NotNull(message = "The number of participants cannot be null.")
    @Min(value = 0, message = "The number of participants must be at least 0.")
    private Integer numberOfParticipants;

    @NotBlank(message = "The location cannot be empty.")
    private String location;

    @NotNull(message = "The address cannot be null.")
    @Valid
    @Embedded
    private Address address;

    @NotNull(message = "The amount cannot be null.")
    @DecimalMin(value = "0.0", inclusive = false, message = "The amount must be greater than 0.")
    private Double amount;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
    private List<Hunting> huntings = new ArrayList<>();

    @OneToMany(mappedBy = "competition")
    private List<Ranking> rankings = new ArrayList<>();

    @PrePersist
    private void generateCode() {
        this.code = address.getCity().substring(0, 3) + date;
    }
}