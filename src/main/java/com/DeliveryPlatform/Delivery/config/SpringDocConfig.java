package com.DeliveryPlatform.Delivery.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SpringDocConfig{
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("DeliveryAPI")
                        .description("campus RESTful API")
                        .version("v0.0.1")
                        .license(new License().name("CV engineers")))
                .externalDocs(new ExternalDocumentation()
                        .description("Issues Documentation")
                        .url("https://****"));
    }
}