package org.applicationcontextaware.example;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Triangle implements ApplicationContextAware, BeanNameAware {
	
	private Point pointA;
	private Point pointB;
	private Point pointC;
	private ApplicationContext context = null;
	
	
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
		System.out.println("Point A = ("+getPointA().getX() +", " +getPointA().getY() + ")"); 
		System.out.println("Point B = ("+getPointB().getX() +", " +getPointB().getY() + ")"); 
		System.out.println("Point C = ("+getPointC().getX() +", " +getPointC().getY() + ")"); 
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
		
	}
	
	public void getPointAContext() {
		Point point = (Point) context.getBean("pointA");
		System.out.println( "Point A Accessed separately using application context aware "
				+ "("+point.getX() + "," + point.getY() + ")" );
	}

	@Override
	//To get the bean name
	public void setBeanName(String beanName) {
		System.out.println("bean name is : " + beanName);
	}
	
	public void myInit() {
		System.out.println("Initalization Method called from spring.xml file");
	}
	
	public void myDestroy() {
		System.out.println("Destroy method is called from spring.xml file");
	}

}
