package com.senac.reader.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI springNewsApi() {
	      return new OpenAPI()
	              .info(new Info().title("Reader - Spring Boot")
	              .description("Documentação da API - Senac PI")
	              .version("v0.0.1")
	              .license(new License().name("Apache 2.0").url("https://github.com/From-Earth/API-Reader/blob/main/LICENSE")))
	              .externalDocs(new ExternalDocumentation()
	              .url("https://github.com/From-Earth/API-Reader"));
	              
	  }
}
