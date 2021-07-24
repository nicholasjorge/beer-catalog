package dev.georgetech.beercatalog;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI beerCatalogOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .addServersItem(new Server()
                        .url("http://localhost:9090/beer-catalog"))
                .info(new Info()
                        .title("Beer Catalog API")
                        .description("Beer search and save simple application")
                        .version("v0.0.1")
                        .contact(new Contact()
                                .name("George Nicolae")
                                .email("nicolae.george.9@gmail.com")));
    }

    @Bean
    public GroupedOpenApi beersApi() {
        return GroupedOpenApi.builder()
                .group("beers")
                .pathsToMatch("/beers/**")
                .build();
    }

    @Bean
    public GroupedOpenApi catalogApi() {
        return GroupedOpenApi.builder()
                .group("catalog")
                .pathsToMatch("/api/**")
                .build();
    }
}