package com.ouharri.aftas.core;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * Configuration class for OpenAPI documentation settings.
 *
 * @author <a href="mailto:ouharrioutman@gmail.com">ouharri outman</a>
 */
@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "ouharri",
                        email = "ouharri.outman+spring@gmail.com",
                        url = "https://ouharri.ma"
                ),
                description = "OpenApi documentation for Jobinow API",
                title = "OpenApi specification - ouharri",
                version = "1.0",
                license = @License(
                        name = "Licence name",
                        url = "https://some-url.com"
                ),
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "PROD ENV",
                        url = "http://54.162.210.15:8085"
                ),
                @Server(
                        description = "Test ENV",
                        url = "http://localhost:8084    "
                ),
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8085"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}