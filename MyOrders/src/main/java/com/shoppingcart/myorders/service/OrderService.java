package com.shoppingcart.myorders.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.UpdateResult;
import com.shoppingcart.myorders.model.MyOrders;
import com.shoppingcart.myorders.model.Products;
import com.shoppingcart.myorders.repository.OrdersRepository;

@Service
public class OrderService {

	private static final Log log = LogFactory.getLog(OrderService.class);

	@Autowired
	private OrdersRepository orderRepo;
	@Autowired
	private MongoTemplate mongoTemplate;
//	@Autowired
//	private MongoOperations template;

	public MyOrders createOrders(MyOrders orders) {
		try {
			MyOrders response = orderRepo.insert(orders);
			return response;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

	public List<MyOrders> getMyOrders(String userId) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("userid").is(userId));
			query.with(Sort.by(Sort.Direction.DESC, "productOrderTime"));
//			System.out.println("test11: " + mongoTemplate.find(query, MyOrders.class).isEmpty());
			return mongoTemplate.find(query, MyOrders.class);
			// return
			// mongoTemplate.find(Query.query(Criteria.where("userid").is(userId)),MyOrders.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}

	}

//	public int saveOrders(Products product, String userId) {
//		try {
////			Update update = new Update();
//			Query query = new Query();
//			query.addCriteria(Criteria.where("userid").is(userId));
////			System.out.println("Orders " + orders);
////			update.push("product", orders);
////			update.set("grandTotal", orders.getGrandTotal());
////			UpdateResult ordersResp = mongoTemplate.updateFirst(query, update, MyOrders.class);
////			System.out.println("Orders Response: " + ordersResp);
//			
//			MyOrders order = template.findOne(query, MyOrders.class);
//			order.getProduct().add(product);
//			template.save(order);
//			
//			return 1;
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("Exception" + e);
//			return 0;
//		}
//	}
}
