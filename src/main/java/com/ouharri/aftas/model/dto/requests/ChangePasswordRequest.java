package com.ouharri.aftas.model.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO class representing a request to change the user's password.
 * Contains fields for the current password, new password, and confirmation password.
 *
 * @author Ouharri Outman
 * @version 1.0
 */
public record ChangePasswordRequest(
        @NotBlank(message = "Current password is required")
        String currentPassword,

        @NotBlank(message = "New password is required")
        @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
        String newPassword,

        @NotBlank(message = "Password confirmation is required")
        @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
        String confirmationPassword
) implements _Request {
}