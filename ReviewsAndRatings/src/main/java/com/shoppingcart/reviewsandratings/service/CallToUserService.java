package com.shoppingcart.reviewsandratings.service;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shoppingcart.reviewsandratings.controller.ReviewsAndRatingsController;
import com.shoppingcart.reviewsandratings.model.UserDetails;

@Service
public class CallToUserService {
	
	private static final Log log = LogFactory.getLog(ReviewsAndRatingsController.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${user-service-url}")
	private String userServiceURL;
	
	@HystrixCommand(fallbackMethod = "getUserDetailsFallback")
	public String getUserDetails(HttpHeaders headers) {

		try {
			
			HttpEntity<UserDetails> entity = new HttpEntity<UserDetails>(headers);
			ResponseEntity<UserDetails> userDetails = restTemplate.exchange(userServiceURL,
					HttpMethod.GET, entity, UserDetails.class);
			System.out.println("Data from user service: " + userDetails.getBody().getFirstName() + " "
					+ userDetails.getBody().getLastName());
			

			return (userDetails.getBody().getFirstName() + " " + userDetails.getBody().getLastName());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}

	}
	
	public String getUserDetailsFallback(HttpHeaders headers) {
		//Here You can get the name stored in cache because of previous request, throw error or You can just set default name
		return "Default Name";
	}

}
