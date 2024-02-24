package com.ouharri.aftas.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuration class for WebClient.
 * This class sets up a WebClient bean for use in the application, particularly for
 * making web requests, such as OAuth2 token introspection requests.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">ouharri outman</a>
 */
@Component
public class WebClientConfig {

    @Value("${spring.security.oauth2.resourceserver.opaque-token.introspection-uri}")
    private String introspectUri;

    /**
     * Creates a WebClient bean with a base URL configured for token introspection.
     * The WebClient is used for making HTTP requests to external services, in this case,
     * it's configured for OAuth2 token introspection based on the application properties.
     *
     * @return A configured instance of WebClient.
     */
    @Bean
    public WebClient userInfoClient() {
        return WebClient.builder().baseUrl(introspectUri).build();
    }
}