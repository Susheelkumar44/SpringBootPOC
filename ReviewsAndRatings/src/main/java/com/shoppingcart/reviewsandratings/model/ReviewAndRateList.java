package com.shoppingcart.reviewsandratings.model;

public class ReviewAndRateList {
	
	private String userID;
	private String username;
	private float rating;
	private String review;
	
	public ReviewAndRateList(String userID, String username, float rating, String review) {
		super();
		this.userID = userID;
		this.username = username;
		this.rating = rating;
		this.review = review;
	}

	public ReviewAndRateList() {
		// TODO Auto-generated constructor stub
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}
	
	
	
	

}
