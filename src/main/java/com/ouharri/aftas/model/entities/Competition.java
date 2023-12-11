package com.ouharri.aftas.model.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "competition")
public class Competition extends AbstractEntity {
    @Pattern(regexp = "^[a-zA-Z]{3}-\\d{2}-\\d{2}-\\d{2}$", message = "The code must follow the specified format.")
    private String code = "ims-22-12-23";

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

    @Valid
    @Embedded
    @NotNull(message = "The address cannot be null.")
    private Address address = new Address();

    @NotNull(message = "The amount cannot be null.")
    @DecimalMin(value = "0.0", inclusive = false, message = "The amount must be greater than 0.")
    private Double amount;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
    private List<Hunting> huntings = new ArrayList<>();

    @OneToMany(mappedBy = "competition")
    private List<Ranking> rankings = new ArrayList<>();

    @PrePersist
    private void generateCode() {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        this.code = address.getCity().substring(0, 3) + "-" + sdf.format(date);
    }
}