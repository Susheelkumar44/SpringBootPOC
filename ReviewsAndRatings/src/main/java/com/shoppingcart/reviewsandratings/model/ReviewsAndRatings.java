package com.shoppingcart.reviewsandratings.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="reviewsandratings")
public class ReviewsAndRatings {
	
	@Id
	private String id;
	private String productID;
	private List<ReviewAndRateList> reviews;
	
	public ReviewsAndRatings(String id, String productID, List<ReviewAndRateList> reviews) {
		super();
		this.id = id;
		this.productID = productID;
		this.reviews = reviews;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public List<ReviewAndRateList> getReviews() {
		return reviews;
	}

	public void setReviews(List<ReviewAndRateList> reviews) {
		this.reviews = reviews;
	}
	
	
	
	
}
