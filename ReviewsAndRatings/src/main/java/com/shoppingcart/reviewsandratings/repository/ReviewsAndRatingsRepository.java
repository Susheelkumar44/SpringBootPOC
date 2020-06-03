package com.shoppingcart.reviewsandratings.repository;

import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongodb.client.FindIterable;
import com.shoppingcart.reviewsandratings.model.ReviewsAndRatings;

public interface ReviewsAndRatingsRepository extends MongoRepository<ReviewsAndRatings, String>{


	


}
