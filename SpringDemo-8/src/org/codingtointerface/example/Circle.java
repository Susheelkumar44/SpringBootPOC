package org.codingtointerface.example;

public class Circle implements Shape {
	
	private Point center;

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}
	
	@Override
	public void Draw() {
		System.out.println("Drawing Circle");
		System.out.println("Point  = ("+center.getX() +", " +center.getY() + ")"); 
		
	}

}
