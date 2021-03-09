package com.obedera.playlist.config;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
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
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.obedera.playlist")).paths(PathSelectors.regex("/api.*"))
				.build().apiInfo(metaInfo());

	}

	private ApiInfo metaInfo() {
		return new ApiInfo("Playlist API REST", "API REST de cadastro de playlists", "1.0", "Terms of Service",
				new Contact("Obede Silva", "https://github.com/Obedera/", "obede.silva7@gmail.com"), "License of API",
				"API license URL", Collections.emptyList());
	}
}
