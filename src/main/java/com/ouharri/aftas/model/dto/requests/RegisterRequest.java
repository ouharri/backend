package com.ouharri.aftas.model.dto.requests;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO class representing a user registration request.
 * Contains fields for the first name, last name, email, password, and confirmation password.
 *
 * @author Ouharri Outman
 * @version 1.0
 */
public record RegisterRequest(
        @NotNull(message = "First name must be present")
        @Size(min = 1, message = "First name cannot be empty")
        @Size(max = 30, message = "First name is too long")
        String firstname,

        @Size(max = 30, message = "Last name is too long")
        String lastname,

        @Email(message = "Email was not provided")
        @Size(max = 80, message = "Email is too long")
        @Column(unique = true, table = "_user")
        String email,

        @NotEmpty(message = "Password cannot be empty")
        @Size(min = 8, message = "Password is too short")
        String password,

        @NotEmpty(message = "Password confirmation cannot be empty")
        @Size(min = 8, message = "Password confirmation is too short")
        String confirmationPassword
) implements _Request {
}
