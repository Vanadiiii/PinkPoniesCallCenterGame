package ru.ponies.pink.configuration;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
@RequiredArgsConstructor
public class SwaggerConfig {
    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Bean
    public Docket swagger() {
        Contact contact = new Contact("Call-center-game", "https://your-url.ru", "vanadiiii42@gmail.com");
        ApiInfo apiInfo = new ApiInfo(
                "Call center game",
                "just cool game for this cool call-center",
                "1.0.0",
                null,
                contact,
                null,
                null,
                Collections.singletonList(new StringVendorExtension("name", "value"))
        );
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.ponies.pink.web"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(apiInfo)
                .securityContexts(
                        List.of(
                                SecurityContext.builder()
                                        .securityReferences(defaultAuth())
                                        .forPaths(PathSelectors.any())
                                        .build())
                )
                .securitySchemes(
                        List.of(
                                new ApiKey("JWT", AUTHORIZATION_HEADER, "header")
                        )
                );
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference("JWT", authorizationScopes));
    }
}