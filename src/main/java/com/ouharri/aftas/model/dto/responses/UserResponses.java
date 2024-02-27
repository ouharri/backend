package com.ouharri.aftas.model.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ouharri.aftas.model.dto.basic.AddressDto;
import com.ouharri.aftas.model.entities.User;
import com.ouharri.aftas.model.enums.Gender;
import com.ouharri.aftas.model.enums.Role;
import com.ouharri.aftas.model.enums.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

/**
 * DTO (Data Transfer Object) representing user-related responses.
 * This class is designed to carry user-related information in a format suitable for response payloads.
 *
 * <p>The fields in this class provide details about the user, including personal information,
 * contact details, and addressing information. It is used to transfer user data between different
 * layers of the application, primarily for response purposes.</p>
 *
 * @see User
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponses extends AbstractResponse<UUID> {
    /**
     * The URL to the user's image.
     */
    String image;

    /**
     * The phone number of the user.
     * Must match the format '0XXXXXXXXX' where X is a digit.
     */
    @Pattern(message = "Phone number must match the format '0XXXXXXXXX'", regexp = "0\\d{9}")
    String phoneNumber;

    /**
     * The email address of the user.
     * Must be a valid email format and should not exceed 80 characters.
     */
    @Size(message = "Email is too long", max = 80)
    @Email(message = "Email was not provided")
    String email;

    /**
     * The first name of the user.
     * Must be present and not empty.
     */
    @NotNull(message = "FirstName must be present")
    @Size(message = "Firstname cannot be empty", min = 1)
    String firstname;

    /**
     * The last name of the user.
     * Should not exceed 30 characters.
     */
    @Size(message = "Lastname is too long", max = 30)
    String lastname;

    /**
     * The gender of the user.
     */
    Gender gender;

    /**
     * The status of the user.
     */
    UserStatus status;

    /**
     * The addressing information of the user.
     */
    AddressDto address;

    /**
     * The role of the user.
     */
    Role role;

    /**
     * Indicates whether the user's account is enabled.
     * A disabled account cannot be used for authentication.
     * Default value is {@code true}, meaning the account is enabled by default.
     */
    boolean enabled;
}