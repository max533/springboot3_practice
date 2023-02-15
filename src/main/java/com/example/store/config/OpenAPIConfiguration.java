package com.example.store.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "BaaSid API Demo",
                version = "${api.version}",
                contact = @Contact(name = "Jeff", email = "abc@example.com", url = "")
        )
)

@SecurityScheme(
        name = "JWT Authentication",
        description = "Provide the JWT token. JWT token can be obtained from the Login API. " +
                "For testing, use the credentials <strong>bassid/password</strong>",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenAPIConfiguration {
}
