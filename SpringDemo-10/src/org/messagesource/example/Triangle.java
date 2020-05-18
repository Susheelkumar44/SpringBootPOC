package org.messagesource.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class Triangle {
	
	private Point pointA;
	private Point pointB;
	private Point pointC;
	
	//To get the message source from the bean specified in xml file
	@Autowired
	private MessageSource messageSource;
	
	
	public MessageSource getMessageSource() {
		return messageSource;
	}

	
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public Point getPointA() {
		return pointA;
	}
	
	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}

	public Point getPointB() {
		return pointB;
	}
	
	public void setPointB(Point pointB) {
		this.pointB = pointB;
	}
	
	public Point getPointC() {
		return pointC;
	}
	
	public void setPointC(Point pointC) {
		this.pointC = pointC;
	}

	//Custom method
	public void Draw() {
//		System.out.println(this.messageSource.getMessage("drawing.triangle", null, 
//				"default Drawing message", null));
		System.out.println("Point A = ("+getPointA().getX() +", " +getPointA().getY() + ")"); 
		System.out.println("Point B = ("+getPointB().getX() +", " +getPointB().getY() + ")"); 
		System.out.println("Point C = ("+getPointC().getX() +", " +getPointC().getY() + ")"); 
	}

}
