package io.distributedjobscheduler.server.autoconfigure;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configures separate OpenAPI groups for the administration and public APIs.
 *
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Configuration
public class SwaggerAutoConfiguration {

    private static final String API_KEY_TOKEN_AUTH = "TokenAuth";

    private static final String API_KEY_SESSION_AUTH = "SessionAuth";

    private static final String API_KEY_HEADER_NAME = "Token";

    private static final String API_KEY_SESSION_NAME = "Session";

    private static final String LOGIN_PATH = "login";

    /**
     * Creates the public API documentation group.
     *
     * @return public API group
     */
    @Bean
    public GroupedOpenApi createOpenapi() {
        return GroupedOpenApi.builder()
                .group("openapi")
                .packagesToScan("io.distributedjobscheduler.server.openapi")
                .build();
    }

    /**
     * Creates the administration API documentation group.
     *
     * @return administration API group
     */
    @Bean
    public GroupedOpenApi createAdminApi() {
        return GroupedOpenApi.builder()
                .group("admin")
                .packagesToScan("io.distributedjobscheduler.server.admin")
                .addOpenApiCustomizer(openApi -> {
                    openApi.getComponents()
                            .addSecuritySchemes(API_KEY_TOKEN_AUTH, new SecurityScheme()
                                    .type(SecurityScheme.Type.APIKEY)
                                    .in(SecurityScheme.In.HEADER)
                                    .name(API_KEY_HEADER_NAME));
                    openApi.getComponents()
                            .addSecuritySchemes(API_KEY_SESSION_AUTH, new SecurityScheme()
                                    .type(SecurityScheme.Type.APIKEY)
                                    .in(SecurityScheme.In.HEADER)
                                    .name(API_KEY_SESSION_NAME));
                    openApi.getPaths().forEach((path, pathItem) -> {
                        if (!path.contains(LOGIN_PATH)) {
                            pathItem.readOperations().forEach(operation -> operation.addSecurityItem(
                                    new SecurityRequirement()
                                            .addList(API_KEY_TOKEN_AUTH)
                                            .addList(API_KEY_SESSION_AUTH)));
                        }
                    });
                })
                .build();
    }
}
