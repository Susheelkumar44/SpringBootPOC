package com.shoppingcart.reviewsandratings.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AccumulatorOperators.Avg;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import static com.mongodb.client.model.Aggregates.addFields;
import com.mongodb.client.model.Field;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.shoppingcart.reviewsandratings.model.AverageRating;
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
			//this.average(productID);
			Query query = new Query();
			query.addCriteria(Criteria.where("productID").is(productID));
			return mongoTemplate.findOne(query, ReviewsAndRatings.class);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void average(String productID) {
	//Mongo Query
//		db.reviewsandratings.aggregate( [{ $match : { productID: "5e956cd3485ff84bc018107b"} } , 
//	  { "$addFields": {
//		    "ratingAverage": { "$avg": "$reviews.rating" }
//		  }}
//		]).pretty();
		
			
	}
	
	
	
}
