package org.basicannotations.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

public class Triangle {
	
	private Point pointA;
	private Point pointB;
	private Point pointC;
	
	
	public Point getPointA() {
		return pointA;
	}
	
	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}

	public Point getPointB() {
		return pointB;
	}
	/* @qualifier is used to wire specific bean with the name specified with match of xml 
	   See more details in spring.xml file*/
	@Autowired
	@Qualifier("pointBRelated")
	public void setPointB(Point pointB) {
		this.pointB = pointB;
	}
	
	public Point getPointC() {
		return pointC;
	}
	
	/* Since here we are using setter type injection, @autowired annotation has to be placed on the setter
	   @Autowired annotation tries to wire beans with the type, if it finds multiple beans of the same type,
	   as in this example we have pointA, B, C are of same type, then it tries wire by the name exact match, 
	   in our case if we see spring.xml, we are not specifying property tag for pointC, spring autowires pointC 
	   with bean since pointC here has same name match with spring.xml*/

	//@Required
	@Autowired
	public void setPointC(Point pointC) {
		this.pointC = pointC;
	}

	//Custom method
	public void Draw() {
		System.out.println("Point A = ("+getPointA().getX() +", " +getPointA().getY() + ")"); 
		System.out.println("Point B = ("+getPointB().getX() +", " +getPointB().getY() + ")"); 
		System.out.println("Point C = ("+getPointC().getX() +", " +getPointC().getY() + ")"); 
	}

}
