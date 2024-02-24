package com.ouharri.aftas.model.dto.requests;

import com.ouharri.aftas.model.dto.basic.AddressDto;
import com.ouharri.aftas.model.entities.User;
import com.ouharri.aftas.model.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO for creating or updating a {@link User} entity.
 * Represents a user request with details such as password, image, phone number,
 * email, first name, last name, gender, and address.
 */
public record UserRequest(
        String password,

        String image,

        @Pattern(message = "Phone number must match the format '0XXXXXXXXX'", regexp = "0\\d{9}")
        String phoneNumber,

        @Size(message = "Email is too long", max = 80) @Email(message = "Email was not provided")
        String email,

        @NotNull(message = "FirstName must be present") @Size(message = "Firstname cannot be empty", min = 1)
        String firstname,
        @Size(message = "Lastname is too long", max = 30)
        String lastname,
        Gender gender,

        AddressDto address
) implements _Request {
}