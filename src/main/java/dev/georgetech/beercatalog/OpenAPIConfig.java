package dev.georgetech.beercatalog;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "beer-catalog",
                version = "v0.1"
        ),
        servers = @Server(url = "http://localhost:9090/beer-catalog")
)
public class OpenAPIConfig {
}