package org.constructorinjecton.example;

public class Triangle {
	
	private String Type;
	private int Height;
	
	//Constructor
	public Triangle(String type) {
		super();
//		this.setType(type); 
		this.Type=type;
	}
	
	public Triangle(int height) {
		super();
		this.Height = height;
	}
	
	//Constructor Overloading
	public Triangle(String type, int height) {
		this.Height = height;
		this.Type = type;
	}

	//Getters
	public String getType() {
		return Type;
	}
	
	public int getHeight() {
		return Height;
	}
	
	//Custom method
	public void Draw() {
		System.out.println(this.getType() + " Triangle drawn with height "+ this.getHeight()); 
	}

}
