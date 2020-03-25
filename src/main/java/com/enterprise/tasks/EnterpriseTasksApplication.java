package com.enterprise.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class EnterpriseTasksApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnterpriseTasksApplication.class, args);
    }

    @Bean
    public Docket swaggerConfiguaration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/**"))
                .apis(RequestHandlerSelectors.basePackage("com.enterprise"))
                .build()
                .apiInfo(apiInfo());
    }

    public ApiInfo apiInfo() {
        return new ApiInfo(
                "Enterprise Tasks API",
                "Gestion de Management (Projects, Personnels, ..) d'une PME/Start-Up",
                "1.0",
                "Private Application",
                new Contact("Randrin Nzeukang", "https://github.com/randrin", "nzeukangrandrin@gmail.com"),
                "API Licence",
                "https://github.com/randrin",
                Collections.emptyList()
        );
    }
}

