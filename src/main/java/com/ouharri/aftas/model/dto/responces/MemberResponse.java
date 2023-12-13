package com.ouharri.aftas.model.dto.responces;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ouharri.aftas.model.entities.Member;
import com.ouharri.aftas.model.enums.IdentityDocumentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Date;

/**
 * DTO for {@link Member}
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MemberResponse extends AbstractResponse {

    @NotBlank(message = "Name cannot be blank.")
    private String name;

    @NotBlank(message = "Family name cannot be blank.")
    private String familyName;

    @NotNull(message = "Accession date cannot be null.")
    private Date accessionDate;

    @NotBlank(message = "Nationality cannot be blank.")
    private String nationality;

    @NotNull(message = "Identity document type cannot be null.")
    private IdentityDocumentType identityDocument;

    @NotBlank(message = "Identity number cannot be blank.")
    private String identityNumber;
}