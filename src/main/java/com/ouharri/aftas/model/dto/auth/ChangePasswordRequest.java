package com.ouharri.aftas.model.dto.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO class representing a request to change the user's password.
 * Contains fields for the current password, new password, and confirmation password.
 *
 * @author Ouharri Outman
 * @version 1.0
 */
@Getter
@Setter
@Builder
public class ChangePasswordRequest {
    /**
     * The user's current password.
     */
    @NotBlank(message = "Current password is required")
    private String currentPassword;

    /**
     * The new password to be set.
     */
    @NotBlank(message = "New password is required")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String newPassword;

    /**
     * Confirmation of the new password.
     */
    @NotBlank(message = "Password confirmation is required")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String confirmationPassword;
}