package com.ouharri.aftas.model.dto.requests;

import com.ouharri.aftas.model.dto.responses.UserResponses;
import com.ouharri.aftas.model.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChangeRoleRequest(
        @NotBlank(message = "User is required")
        UserResponses user,

        @NotNull(message = "Role is required")
        Role role

) implements _Request {
}
