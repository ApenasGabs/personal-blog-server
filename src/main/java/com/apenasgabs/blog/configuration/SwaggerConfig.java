package com.apenasgabs.blog.configuration;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI springBlogPessoalOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Personal blog project")
                        .description("This is my personal blog server")
                        .version("v0.0.1")
                        .license(new License()
                                .name("Gabriel Rodrigues")
                                .url("https://www.apenasgabs.dev/"))
                        .contact(new Contact()
                                .name("Gabriel Rodrigues")
                                .url("https://github.com/apenasgabs")
                                .email("apenasgabs.dev@gmail.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Github")
                        .url("https://github.com/apenasgabs/"));
    }
    @Bean
    OpenApiCustomizer customerGlobalHeaderOpenApiCustomizer() {

        return openApi -> {
            openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {

                ApiResponses apiResponses = operation.getResponses();

                apiResponses.addApiResponse("200", createApiResponse("Success!"));
                apiResponses.addApiResponse("201", createApiResponse("Object Persisted!"));
                apiResponses.addApiResponse("204", createApiResponse("Object Deleted!"));
                apiResponses.addApiResponse("400", createApiResponse("Request Error!"));
                apiResponses.addApiResponse("401", createApiResponse("Unauthorized Access!"));
                apiResponses.addApiResponse("403", createApiResponse("Forbidden Access!"));
                apiResponses.addApiResponse("404", createApiResponse("Object Not Found!"));
                apiResponses.addApiResponse("500", createApiResponse("Application Error!"));

            }));
        };
    }

    private ApiResponse createApiResponse(String message) {

        return new ApiResponse().description(message);

    }
}
