package com.ouharri.aftas.security;

import com.ouharri.aftas.model.dto.Oauth.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Custom implementation of OpaqueTokenIntrospector for Google OAuth2 tokens.
 * This class is used to introspect OAuth2 tokens, validating them and extracting
 * the necessary user information for authentication and authorization purposes.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">Ouharri Outman</a>
 */
@RequiredArgsConstructor
public class GoogleOpaqueTokenIntrospector implements OpaqueTokenIntrospector {

    private final WebClient userInfoClient;

    /**
     * Introspects an OAuth2 token, validating it and extracting user information.
     * This method uses the WebClient to call Google's userinfo endpoint, retrieves the user info,
     * and then constructs an OAuth2AuthenticatedPrincipal with the user details.
     *
     * @param token The OAuth2 token to be introspected.
     * @return OAuth2AuthenticatedPrincipal containing the user's information.
     */
    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        var userInfo = userInfoClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/oauth2/v3/userinfo")
                        .queryParam("access_token", token)
                        .build())
                .retrieve()
                .bodyToMono(UserInfo.class)
                .block();
        assert userInfo != null;
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("sub", userInfo.sub());
        attributes.put("name", userInfo.name());
        return new OAuth2IntrospectionAuthenticatedPrincipal(userInfo.name(), attributes, null);
    }
}