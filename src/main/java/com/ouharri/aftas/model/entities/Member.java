package com.ouharri.aftas.model.entities;

import com.ouharri.aftas.model.enums.IdentityDocumentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class Member extends AbstractEntity {
    @NotBlank(message = "Name cannot be blank.")
    private String name;

    @NotBlank(message = "Family name cannot be blank.")
    private String familyName;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "Accession date cannot be null.")
    private Date accessionDate;

    @NotBlank(message = "Nationality cannot be blank.")
    private String nationality;

    @NotNull(message = "Identity document type cannot be null.")
    private IdentityDocumentType identityDocument;

    @NotBlank(message = "Identity number cannot be blank.")
    private String identityNumber;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Hunting> huntings = new ArrayList<>();

    @OneToMany(mappedBy = "id.member", cascade = CascadeType.ALL)
    private List<Ranking> rankings;
}