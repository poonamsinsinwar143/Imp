package com.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableWebMvc
public class InsuranceManagementPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsuranceManagementPlatformApplication.class, args);
	}

	@Bean
	public Docket api() {

		RequestParameterBuilder customHeaders = new RequestParameterBuilder();
		RequestParameter requestParameter = customHeaders.name("Authorization").in(ParameterType.HEADER).required(true)
				.build();
		List<RequestParameter> list = new ArrayList<>();
		list.add(requestParameter);

		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.imp.controller")).paths(PathSelectors.any()).build()
				.globalRequestParameters(list);
	}

}
