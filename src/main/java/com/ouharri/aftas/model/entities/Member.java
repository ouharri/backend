package com.ouharri.aftas.model.entities;

import com.ouharri.aftas.model.enums.IdentityDocumentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a member of the sports club.
 * Extends the AbstractEntity class.
 *
 * @author ouharri
 * @version 1.0
 */
@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class Member extends AbstractEntity {

    /**
     * The name of the member.
     */
    @NotBlank(message = "Name cannot be blank.")
    private String name;

    /**
     * The family name of the member.
     */
    @NotBlank(message = "Family name cannot be blank.")
    private String familyName;

    /**
     * The date of accession to the club.
     */
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Accession date cannot be null.")
    private Date accessionDate;

    /**
     * The nationality of the member.
     */
    @NotBlank(message = "Nationality cannot be blank.")
    private String nationality;

    /**
     * The type of identity document.
     */
    @NotNull(message = "Identity document type cannot be null.")
    private IdentityDocumentType identityDocument;

    /**
     * The identity number of the member.
     */
    @NotBlank(message = "Identity number cannot be blank.")
    private String identityNumber;

    /**
     * The list of huntings associated with this member.
     */
    @OneToMany(
            mappedBy = "huntingCompositeKey.member",
            cascade = CascadeType.ALL
    )
    private List<Hunting> huntings = new ArrayList<>();

    /**
     * The list of rankings associated with this member.
     */
    @OneToMany(
            mappedBy = "id.member",
            cascade = CascadeType.ALL
    )
    private List<Ranking> rankings;
}