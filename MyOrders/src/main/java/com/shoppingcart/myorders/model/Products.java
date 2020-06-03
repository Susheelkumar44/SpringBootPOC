package com.shoppingcart.myorders.model;

public class Products {

	private String productID;
	private String productName;
	private int quantity;
	private float subTotal;
	
	private String imageURL;
	
	
//	public Products(String productID, String productName, int quantity, String imageURL) {
//		super();
//		this.productID = productID;
//		this.productName = productName;
//		this.quantity = quantity;
//		this.imageURL = imageURL;
//	}
	public Products(String productID, String productName, int quantity, float subTotal, String imageURL) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.quantity = quantity;
		this.subTotal = subTotal;
		this.imageURL = imageURL;
	}
	
	
	public String getProductID() {
		return productID;
	}
	
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public float getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}
	
	
}
