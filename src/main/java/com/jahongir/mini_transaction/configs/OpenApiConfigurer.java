package com.jahongir.mini_transaction.configs;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jahongir
 * @created 04/02/23 - 21:04
 * @project Mini_transaction/IntelliJ IDEA
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Mini transaction App", version = "v1",
                description = "All backend apis of transaction app is included using several spring and other tools",
                contact = @Contact(name = "Jahongir Atametov",
                        url = "https://www.linkedin.com/in/jahongir-atametov-06a582252/", email = "atametov97@mail.ru"),
                license = @License(name = "Apache Foundation", url = "http://apache.org")
        )


)

public class OpenApiConfigurer {
    @Bean
    public OpenAPI customizeOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}
