package com.backendtravelbox.user.shared;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI platformOpenApi() {
        var openApi = new OpenAPI();
        openApi.info(new Info().title("Backend User").version("v1.0.0"));
        return openApi;
    }
}
