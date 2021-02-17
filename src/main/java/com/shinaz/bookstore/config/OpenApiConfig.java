package com.shinaz.bookstore.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Book Store")
                        .description("Java Spring Boot Application that Represents a Simple Bookstore.")
                        .version("1.0")
                        .termsOfService("http://swagger.io/terms/")
                        .contact(new Contact().name("Shinaz").email("shinazms@gmail.com"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))


                );
    }
}
