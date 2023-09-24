package org.acme;

import org.eclipse.microprofile.openapi.annotations.Components;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlow;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlows;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

/**
 * Defines OpenAPI configurations for the Quarkus application, for more information you must see
 * <a href="https://quarkus.io/guides/openapi-swaggerui>Using OpenAPI and Swagger UI</a>
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Acme Financial Service API",
                version = "1.0.1"),
        components = @Components(
                securitySchemes = {
                        @SecurityScheme(securitySchemeName = "acme-financial-oauth",
                                type = SecuritySchemeType.OAUTH2,
                                flows = @OAuthFlows(
                                        clientCredentials = @OAuthFlow(
                                                authorizationUrl = "http://localhost:8281/auth/realms/kogito/protocol/openid-connect/auth",
                                                tokenUrl = "http://localhost:8281/auth/realms/kogito/protocol/openid-connect/token",
                                                scopes = {})))
                }))
public class AcmeFinancialApplication extends javax.ws.rs.core.Application {

}
