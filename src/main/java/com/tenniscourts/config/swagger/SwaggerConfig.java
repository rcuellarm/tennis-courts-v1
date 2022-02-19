package com.tenniscourts.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.tenniscourts"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(tennisCourtAPIInfo()).tags(
                        new Tag("Guest Controller", "REST API for managing Guests"),
                        new Tag("Reservation Controller", "REST API for managing Reservations"),
                        new Tag("Schedule Controller", "REST API for managing Schedules"),
                        new Tag("Tennis Court Controller", "REST API for managing Tennis Court")
                );
    }

    private ApiInfo tennisCourtAPIInfo() {
        ApiInfo apiInfo = new ApiInfoBuilder().
                title("Tennis Courts - Challenge API").
                description("Backend API for a simple reservation platform for tennis players").
                version("1.0").
                contact(new Contact("Ruddy Cuellar",
                        "https://www.linkedin.com/in/ruddycuellarmayorga/?locale=en_US",
                        "cuellar.ra@pucp.pe")).
                license("Apache License 2.0").
                licenseUrl("https://www.apache.org/licenses/LICENSE-2.0").
                build();
        return apiInfo;
    }
}
