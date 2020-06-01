package com.shoppingcart.reviewsandratings.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shoppingcart.reviewsandratings.model.ReviewsAndRatings;

public interface ReviewsAndRatingsRepository extends MongoRepository<ReviewsAndRatings, String>{

}
