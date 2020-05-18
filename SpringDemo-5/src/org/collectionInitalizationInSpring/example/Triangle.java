package org.collectionInitalizationInSpring.example;

import java.util.List;

public class Triangle {
	
	private List<Point> points;

	public void setPoints(List<Point> points) {
		this.points = points;
	}


	//Custom method
	public void Draw() {
		
		for (Point point: points) {
			System.out.println("Point = ("+point.getX() +", " +point.getY() + ")"); 
		}
	}
}
