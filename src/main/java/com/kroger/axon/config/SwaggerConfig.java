package com.kroger.axon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final String TITLE = "Kroger Axon Example REST API";
	private static final String DESCRIPTION = "API for Axon REST Services";
	private static final String VERSION = "1.0.0";

	private final Contact CONTACT = new Contact("Marc Risney", "https://github.com/mrisney", "marc.risney@gmail.com");

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.kroger.axon.controller"))
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title(TITLE)
				.description(DESCRIPTION)
				.version(VERSION)
				.contact(CONTACT)
				.build();
	}
}
