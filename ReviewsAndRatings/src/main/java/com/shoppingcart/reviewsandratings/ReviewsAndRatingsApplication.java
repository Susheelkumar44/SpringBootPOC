package com.shoppingcart.reviewsandratings;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableCircuitBreaker
public class ReviewsAndRatingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewsAndRatingsApplication.class, args);
	}
	
	@Bean
	//@LoadBalanced
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Bean
	public Docket SwaggerConfiguration() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/review/*"))
				.apis(RequestHandlerSelectors.basePackage("com.shoppingcart.reviewsandratings"))
				.build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfo(
				"ReviewsAndRatings API", 
				"API details for adding and getting reviews and ratings for a product", 
				"1.0", 
				"", 
				new springfox.documentation.service.Contact("Susheelkumar S", "My URL", "My Email"),
				"API License",
				"", 
				Collections.emptyList()) ;
	}

}
