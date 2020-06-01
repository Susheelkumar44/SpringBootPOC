package com.shoppingcart.reviewsandratings.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.shoppingcart.reviewsandratings.model.ReviewAndRateList;
import com.shoppingcart.reviewsandratings.model.ReviewsAndRatings;
import com.shoppingcart.reviewsandratings.repository.ReviewsAndRatingsRepository;

@Service
public class ReviewsAndRatingsService {

	@Autowired
	private ReviewsAndRatingsRepository ratingsRepo;
	@Autowired
	private MongoTemplate mongoTemplate;

	public int insertReview(ReviewAndRateList myreviews, String productID) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("productID").is(productID));
			ReviewsAndRatings reviews = mongoTemplate.findOne(query, ReviewsAndRatings.class);
			System.out.println("reviews "+reviews.getReviews());
			reviews.getReviews().add(myreviews);
			mongoTemplate.save(reviews);
			return 1;
			
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public ReviewsAndRatings getReviewsAndRatings(String productID) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("productID").is(productID));
			return mongoTemplate.findOne(query, ReviewsAndRatings.class);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
