package com.api.quotationmanagement.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class OpenApiConfig {

    @Value("${server.port}")
    private int port;

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Employee Management API")
                        .description("RESTful API documentation for CRUD implementation of employee from MySQL database.")
                        .contact(new Contact().email("victorbologna@hotmail.com"))
                        .version("1.0.0"))
                .servers(Collections.singletonList(new Server().url("http://localhost:" + port)
                        .description("Employee Management API")));
    }
}
