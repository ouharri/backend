package com.ouharri.aftas.model.dto.Oauth;

/**
 * Data Transfer Object (DTO) for representing an OAuth token.
 * This record is used to encapsulate and transfer the token data,
 * typically between different layers of the application, such as controllers and services.
 */
public record TokenDto(String token) {
}
