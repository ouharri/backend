package com.ouharri.aftas.model.entities;

import com.ouharri.aftas.validations.Annotation.EndTimeAfterStartTime;
import com.ouharri.aftas.validations.Annotation.FutureDate;
import com.ouharri.aftas.validations.interfaces.DateTimeValidator;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a competition organized by the Aftas sports club for underwater hunting.
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
@EndTimeAfterStartTime
@Table(name = "competition")
public class Competition extends AbstractEntity<UUID> implements DateTimeValidator {

    /**
     * The unique code associated with the competition.
     * Follows the format: [city abbreviation]-[yy-MM-dd]
     */
    @Pattern(regexp = "^[a-zA-Z]{3}-\\d{2}-\\d{2}-\\d{2}$", message = "The code must follow the specified format.")
    private String code = "aaa-22-12-23";

    /**
     * The title of the competition.
     */
    @NotBlank(message = "The title cannot be empty.")
    private String title;

    /**
     * The date of the competition.
     */
    @FutureDate
    @Temporal(TemporalType.DATE)
    @NotNull(message = "The date cannot be null.")
    private Date date;

    /**
     * The start time of the competition.
     */
    @NotNull(message = "The start time cannot be null.")
    @Temporal(TemporalType.TIME)
    private Time startTime;

    /**
     * The end time of the competition.
     */
    @NotNull(message = "The end time cannot be null.")
    @Temporal(TemporalType.TIME)
    private Time endTime;

    /**
     * The number of participants in the competition.
     */
    @NotNull(message = "The number of participants cannot be null.")
    @Min(value = 0, message = "The number of participants must be at least 0.")
    private int numberOfParticipants;

    /**
     * The location where the competition takes place.
     */
    private String location;

    /**
     * The address associated with the competition.
     */
    @Valid
    @Embedded
    @NotNull(message = "The address cannot be null.")
    private Address address = new Address();

    /**
     * The entry fee amount for the competition.
     */
    @NotNull(message = "The amount cannot be null.")
    @DecimalMin(value = "0.0", inclusive = false, message = "The amount must be greater than 0.")
    private Double amount;

    /**
     * List of hunting records associated with the competition.
     */
    @OneToMany(
            mappedBy = "huntingCompositeKey.competition",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Hunting> huntings = new ArrayList<>();

    /**
     * List of rankings associated with the competition.
     */
    @OneToMany(
            mappedBy = "id.competition",
            cascade = CascadeType.ALL
    )
    private List<Ranking> rankings = new ArrayList<>();

    /**
     * Generates a unique code for the competition based on the city abbreviation and date.
     * This method is executed before the entity is persisted.
     */
    @PrePersist
    private void generateCode() {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        this.code = address.getCity().substring(0, 3) + "-" + sdf.format(date);
    }
}