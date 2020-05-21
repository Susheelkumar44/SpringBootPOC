package com.shoppingcart.myorders.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="myorders")
public class MyOrders {

	@Id
	private String id;
	private String userid;
	private List<Products> products;
	private float grandTotal;
	private Date productOrderTime;
	
	public MyOrders(String id, String userid, List<Products> products, float grandTotal, Date productOrderTime) {
		super();
		this.id = id;
		this.userid = userid;
		this.products = products;
		this.grandTotal = grandTotal;
		this.productOrderTime = productOrderTime;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public List<Products> getProduct() {
		return products;
	}
	public void setProduct(List<Products> products) {
		this.products = products;
	}
	public float getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}
	public Date getProductOrderTime() {
		return productOrderTime;
	}
	public void setProductOrderTime(Date now) {
		this.productOrderTime = now;
	}
	
	
}
