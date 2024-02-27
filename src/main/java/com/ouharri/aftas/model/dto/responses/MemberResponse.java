package com.ouharri.aftas.model.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ouharri.aftas.model.entities.Member;
import com.ouharri.aftas.model.enums.Gender;
import com.ouharri.aftas.model.enums.IdentityDocumentType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.URL;

import java.sql.Date;
import java.util.UUID;

/**
 * DTO for {@link Member}
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MemberResponse extends AbstractResponse<UUID> {

    @NotBlank(message = "Name cannot be blank.")
    private String firstname;

    @NotBlank(message = "Family name cannot be blank.")
    private String lastname;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "Birth date cannot be null.")
    Date birthDate;

    @Email(message = "Email was not provided")
    @NotNull(message = "Email cannot be null.")
    String email;

    @NotNull(message = "Accession date cannot be null.")
    Date accessionDate;

    @NotBlank(message = "Nationality cannot be blank.")
    private String nationality;

    @NotNull(message = "Identity document type cannot be null.")
    private IdentityDocumentType identityDocument;

    @NotBlank(message = "Identity number cannot be blank.")
    private String identityNumber;

    Gender gender;

    @URL
    String image;
}