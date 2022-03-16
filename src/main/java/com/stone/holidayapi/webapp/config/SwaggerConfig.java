package com.stone.holidayapi.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.any;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket caseworkAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Collections.singletonList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.stone.holiday"))
                .paths(any())
                .build();
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(defaultAuth()))
                .build();
    }

    private SecurityReference defaultAuth() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{
                new AuthorizationScope("global", "access endpoint")
        };
        return new SecurityReference("JWT", authorizationScopes);
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }
}
