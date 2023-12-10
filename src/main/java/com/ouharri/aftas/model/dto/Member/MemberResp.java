package com.ouharri.aftas.model.dto.Member;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ouharri.aftas.model.enums.IdentityDocumentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link com.ouharri.aftas.model.entities.Member}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record MemberResp(UUID id, String createdAt, LocalDateTime updatedAt,
                         @NotBlank(message = "Name cannot be blank.") String name,
                         @NotBlank(message = "Family name cannot be blank.") String familyName,
                         @NotNull(message = "Accession date cannot be null.") Date accessionDate,
                         @NotBlank(message = "Nationality cannot be blank.") String nationality,
                         @NotNull(message = "Identity document type cannot be null.") IdentityDocumentType identityDocument,
                         @NotBlank(message = "Identity number cannot be blank.") String identityNumber) implements Serializable {
}