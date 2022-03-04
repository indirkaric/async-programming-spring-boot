package com.indir.async.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    private final String EMAIL = "indir97_karic@outlook.com";
    private final String FULL_NAME = "Indir Karic";
    private final String URL = "https://ba.linkedin.com/in/indir-karic-a95576176";
    private final String TITLE = "Simple API ";
    private final String DESCRIPTION = "Documentation for REST API";
    private final String VERSION = "1.0";
    private final String TERMS_OF_SERVICE = "Terms of Service:" + EMAIL;
    private final String LICENSE = "Apache License Version 2.0";
    private final String LICENSE_URL = "https://www.apache.org/licenses/LICENSE-2.0";

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.any())
                    .paths(PathSelectors.any())
                    .build()
                    .pathMapping("/")
                    .apiInfo(metaData());
    }
    private ApiInfo metaData(){
        Contact contact = new Contact(FULL_NAME, URL, EMAIL);

        return new ApiInfo(
                TITLE,
                DESCRIPTION,
                VERSION,
                TERMS_OF_SERVICE,
                contact,
                LICENSE,
                LICENSE_URL,
                new ArrayList<>()
        );
    }
}
