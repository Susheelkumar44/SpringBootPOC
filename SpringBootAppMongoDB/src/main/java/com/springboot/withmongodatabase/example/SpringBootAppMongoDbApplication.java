package com.springboot.withmongodatabase.example;

import java.util.Collections;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@SpringBootApplication
@EnableSwagger2
public class SpringBootAppMongoDbApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootAppMongoDbApplication.class, args);
	}
	
	@Bean
	public Docket SwaggerConfiguration() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
//				.paths(PathSelectors.ant("/books/*"))
				.apis(RequestHandlerSelectors.basePackage("com.springboot.withmongodatabase.example"))
				.build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfo(
				"Books Management API", 
				"API details for Managing Books details", 
				"1.0", 
				"", 
				new springfox.documentation.service.Contact("Susheelkumar S", "URL", "EMAIL"),
				"API License",
				"", 
				Collections.emptyList()) ;
	}

}
