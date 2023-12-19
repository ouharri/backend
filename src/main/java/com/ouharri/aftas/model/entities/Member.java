package com.ouharri.aftas.model.entities;

import com.ouharri.aftas.model.enums.Gender;
import com.ouharri.aftas.model.enums.IdentityDocumentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.hibernate.validator.constraints.URL;

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
     * The birthdate of the member.
     */
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Birth date cannot be null.")
    private Date birthDate;

    /**
     * The email of the member.
     */
    @Email(message = "Email was not provided")
    @NotNull(message = "Email cannot be null.")
    private String email;

    /**
     * The type of identity document.
     */
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @NotNull(message = "Identity document type cannot be null.")
    private IdentityDocumentType identityDocument;

    /**
     * The identity number of the member.
     */
    @NotBlank(message = "Identity number cannot be blank.")
    private String identityNumber;

    /**
     * The member's gender.
     */
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private Gender gender;

    /**
     * The member's image.
     */
    @URL
    private String image;

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