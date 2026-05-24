package com.schedify.schedify_api.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Schedify - Sistema de Agendamento Inteligente",
        description = "API para gerenciamento de agendamentos inteligentes com detecção de conflitos e geração automática de slots disponíveis.",
        version = "1.0.0",
        contact = @Contact(
            name = "Schedify",
            email = "contato@schedify.com"
        ),
        license = @License(
            name = "MIT"
        )
    )
)
public class OpenApiConfig {
}
