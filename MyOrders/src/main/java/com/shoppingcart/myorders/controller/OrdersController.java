package com.shoppingcart.myorders.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.ApiOperation;

import java.util.Calendar;
import java.util.Date;   
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shoppingcart.myorders.model.MyOrders;
import com.shoppingcart.myorders.model.Products;
import com.shoppingcart.myorders.service.OrderService;
import com.shoppingcart.myorders.model.Response;

@RestController
//@RequestMapping("/orders")
public class OrdersController {
	
	private static final Log log = LogFactory.getLog(OrdersController.class);
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	
	
	JSONObject responseObj = new JSONObject();
	
	Dotenv dotenv = Dotenv.load();

	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/orders")
	@ApiOperation(value="Inserts Orders ordered by customer into Orders Repository",
				  notes = "Customer ID should be provided from token passed as header",	
				  response=Response.class)
	public JSONObject createOrders(@RequestBody MyOrders orders) {
		String userID = this.getUserIDFromToken();
		if (userID == null) {
			response.setStatus(403);
			return this.ResponseGenerator(403, HttpStatus.FORBIDDEN,
					"Invalid token");
		}
		orders.setUserid(userID);
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		orders.setProductOrderTime(now);

		
		MyOrders ordersData = orderService.createOrders(orders);
		if (ordersData == null) {
			return this.ResponseGenerator(502, HttpStatus.BAD_GATEWAY,
					"Error in creating orders");
		}
		return this.ResponseGenerator(200, HttpStatus.OK,
				"Successfull");
	}
	
//	@PutMapping("/orders")
//	public void insertOrder(@RequestBody Products orders) {
//		String UserID = this.getUserIDFromToken();
//		int dbResponse = orderService.saveOrders(orders, UserID);
//		System.out.println(dbResponse);
//	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/orders")
	@ApiOperation(value="Gets all orders sorted by last ordered time",
	  notes = "Customer ID should be provided from token passed as header")
	public List<MyOrders> getMyOrders() {
		String userID = this.getUserIDFromToken();
		System.out.println("Data in get: "+orderService.getMyOrders(userID));
		return orderService.getMyOrders(userID);
	}
	
	//Decoding JSON Token
	public String getUserIDFromToken() {
		try {

		    Jws<Claims> JsonValue = Jwts.parser()
			.setSigningKey(dotenv.get("ACCESS_TOKEN_SECRET").getBytes("UTF-8"))
			.parseClaimsJws(request.getHeader("authorization"));

		    String userID = (String) JsonValue.getBody().get("_id");
		    System.out.println("JSON Values "+ userID);
		    return userID;
		   

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		    return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject ResponseGenerator(int code, HttpStatus type, String message) {
		responseObj.put("code", code);
		responseObj.put("type", type);
		responseObj.put("message", message);
		return responseObj;
	}
}
