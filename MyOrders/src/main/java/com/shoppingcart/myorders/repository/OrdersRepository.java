package com.shoppingcart.myorders.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shoppingcart.myorders.model.MyOrders;

public interface OrdersRepository extends MongoRepository<MyOrders, String> {

}
