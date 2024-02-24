package com.ouharri.aftas.model.dto.Oauth;

/**
 * Record representing the user information obtained from an OAuth provider.
 * This record is used to encapsulate user details such as the subject identifier, name, given name,
 * family name, profile picture URL, email, email verification status, and locale.
 * It is typically used to store and transfer user information within the application
 * after successful authentication with an OAuth provider.
 *
 * @param sub            Subject identifier (unique identifier for the user).
 * @param name           Full name of the user.
 * @param given_name     Given (first) name of the user.
 * @param family_name    Family (last) name of the user.
 * @param picture        URL of the user's profile picture.
 * @param email          Email address of the user.
 * @param email_verified Boolean flag indicating whether the user's email is verified.
 * @param locale         Locale of the user.
 */
public record UserInfo(
        String sub,
        String name,
        String given_name,
        String family_name,
        String picture,
        String email,
        boolean email_verified,
        String locale
) {
}
