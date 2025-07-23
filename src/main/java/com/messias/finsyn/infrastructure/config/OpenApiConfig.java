package com.messias.finsyn.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Controle financeiro")
                        .version("v1")
                        .description("API para gestão financeira, para usuário poderem cadastrar transações, criarem e acompanharem suas metas financeiras")
                        .termsOfService("")
                        .license(new License().name("").url("")));
    }
}
