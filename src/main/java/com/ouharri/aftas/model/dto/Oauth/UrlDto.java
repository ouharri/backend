package com.ouharri.aftas.model.dto.Oauth;

import org.hibernate.validator.constraints.URL;

/**
 * Data Transfer Object (DTO) for representing a URL used in OAuth authentication processes.
 * This record encapsulates a URL, ensuring that it is a valid URL format as per the specified constraint.
 * It is typically used to transfer OAuth-related URLs, like authentication or redirection URLs, between layers of the application.
 */
public record UrlDto(@URL String authURL) {
}