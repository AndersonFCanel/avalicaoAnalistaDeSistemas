package com.acanel.azul_teste.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket restEndpointAPI() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.acanel.azul_teste.controller")).paths(regex("/.*")).build()
				.apiInfo(metaData());

	}

	private ApiInfo metaData() {
		return new ApiInfo("Analista de Sistemas", "Avaliação", "1.0", "Terms of service",
				new Contact("Anderson ferreira Canel", "e-mail", "andersonfcanel@gmail.com"), "MIT License", "https://opensource.org/licenses/MIT",
				Collections.emptyList());
	}

}
