package com.bounteous.student_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Student Management API")
                .version("1.0")
                .description("REST API for managing students using Spring Boot + Gradle + H2 + Swagger"));
    }
}
