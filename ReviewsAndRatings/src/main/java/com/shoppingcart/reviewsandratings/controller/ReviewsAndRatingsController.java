package com.shoppingcart.reviewsandratings.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shoppingcart.reviewsandratings.model.Response;
import com.shoppingcart.reviewsandratings.model.ReviewAndRateList;
import com.shoppingcart.reviewsandratings.model.ReviewsAndRatings;
import com.shoppingcart.reviewsandratings.model.UserDetails;
import com.shoppingcart.reviewsandratings.service.CallToUserService;
import com.shoppingcart.reviewsandratings.service.ReviewsAndRatingsService;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.ApiOperation;

@RestController
public class ReviewsAndRatingsController {

	private static final Log log = LogFactory.getLog(ReviewsAndRatingsController.class);

	@Autowired
	private ReviewsAndRatingsService reviewService;
	@Autowired
	private CallToUserService callToUserService;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	
	@Value("${user-service-url}")
	private String userServiceURL;

	
	JSONObject responseObj = new JSONObject();

	Dotenv dotenv = Dotenv.load();

	@CrossOrigin
	@PutMapping("/review/{productID}")
	@ApiOperation(value="Customer provides reviews and ratings of the products they have purchased",
	  notes = "Customer ID should be provided from token passed as header",	
	  response=Response.class)
	public JSONObject insertReviewsAndRatings(@PathVariable("productID") String productID,
			@RequestBody ReviewAndRateList reviews) {
		String userID = this.getUserIDFromToken();
		reviews.setUserID(userID);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", "Bearer " + request.getHeader("authorization"));
		System.out.println("Headers set are: " + headers);
		reviews.setUsername(callToUserService.getUserDetails(headers));
		
		int responseReturned = reviewService.insertReview(reviews, productID);
		System.out.println("responseReturned :"+responseReturned);
		if (responseReturned == 0) {
			response.setStatus(502);
			return this.ResponseGenerator(502, HttpStatus.BAD_GATEWAY,
					"Error in creating orders");
		}
		return this.ResponseGenerator(200, HttpStatus.OK,
				"Successfull");
	}
	
	@CrossOrigin
	@GetMapping("/review/{productID}")
	@ApiOperation(value="Gets all reviews of the products provided by the customers",
	  notes = "ProductID should be passed as URL/Path parameter")
	public ReviewsAndRatings getReviewsAndRatingsForAProduct(@PathVariable("productID") String productID) {
		ReviewsAndRatings reviewData =  reviewService.getReviewsAndRatings(productID);
		reviewData.getReviews().remove(0);
		return reviewData;
	}
			
	

	// Decoding JSON Token
	public String getUserIDFromToken() {
		try {

			Jws<Claims> JsonValue = Jwts.parser().setSigningKey(dotenv.get("ACCESS_TOKEN_SECRET").getBytes("UTF-8"))
					.parseClaimsJws(request.getHeader("authorization"));

			String userID = (String) JsonValue.getBody().get("_id");
			System.out.println("JSON Values " + userID);
			return userID;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

//	//Gets user details from user service
//	@HystrixCommand(fallbackMethod = "getUserDetailsFallback")
//	public String getUserDetails() {
//
//		try {
//			HttpHeaders headers = new HttpHeaders();
//			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//			headers.add("Authorization", "Bearer " + request.getHeader("authorization"));
//			System.out.println("Headers set are: " + headers);
//			HttpEntity<UserDetails> entity = new HttpEntity<UserDetails>(headers);
//			ResponseEntity<UserDetails> userDetails = restTemplate.exchange(userServiceURL,
//					HttpMethod.GET, entity, UserDetails.class);
//			System.out.println("Data from user service: " + userDetails.getBody().getFirstName() + " "
//					+ userDetails.getBody().getLastName());
//			
//
//			return (userDetails.getBody().getFirstName() + " " + userDetails.getBody().getLastName());
//		} catch (Exception e) {
//			log.error(e.getMessage(), e);
//			return null;
//		}
//
//	}
//	
//	public String getUserDetailsFallback() {
//		//Here You can get the name stored in cache because of previous request, throw error or You can just set default name
//		return "Default Name";
//	}
	
	
	@SuppressWarnings("unchecked")
	private JSONObject ResponseGenerator(int code, HttpStatus type, String message) {
		responseObj.put("code", code);
		responseObj.put("type", type);
		responseObj.put("message", message);
		return responseObj;
	}

}
