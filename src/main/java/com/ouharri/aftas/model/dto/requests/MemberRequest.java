package com.ouharri.aftas.model.dto.requests;

import com.ouharri.aftas.model.enums.IdentityDocumentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

/**
 * DTO for {@link com.ouharri.aftas.model.entities.Member}
 */
public record MemberRequest(
        @NotBlank(message = "Name cannot be blank.")
        String name,

        @NotBlank(message = "Family name cannot be blank.")
        String familyName,

        @NotNull(message = "Accession date cannot be null.")
        Date accessionDate,

        @NotBlank(message = "Nationality cannot be blank.")
        String nationality,

        @NotNull(message = "Identity document type cannot be null.")
        IdentityDocumentType identityDocument,

        @NotBlank(message = "Identity number cannot be blank.")
        String identityNumber
) implements _Request {
}