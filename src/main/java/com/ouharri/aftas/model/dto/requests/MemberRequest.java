package com.ouharri.aftas.model.dto.requests;

import com.ouharri.aftas.model.entities.Member;
import com.ouharri.aftas.model.enums.Gender;
import com.ouharri.aftas.model.enums.IdentityDocumentType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

import java.sql.Date;

/**
 * DTO for {@link Member}
 */
public record MemberRequest(
        @NotBlank(message = "Name cannot be blank.")
        String firstname,

        @NotBlank(message = "Family name cannot be blank.")
        String lastname,

        @Temporal(TemporalType.DATE)
        @NotNull(message = "Birth date cannot be null.")
        Date birthDate,

        Gender gender,

        @Email(message = "Email was not provided")
        @NotNull(message = "Email cannot be null.")
        String email,

        @NotNull(message = "Accession date cannot be null.")
        Date accessionDate,

        @NotBlank(message = "Nationality cannot be blank.")
        String nationality,

        @NotNull(message = "Identity document type cannot be null.")
        IdentityDocumentType identityDocument,

        @NotBlank(message = "Identity number cannot be blank.")
        String identityNumber,

        @URL
        String image
) implements _Request {
}