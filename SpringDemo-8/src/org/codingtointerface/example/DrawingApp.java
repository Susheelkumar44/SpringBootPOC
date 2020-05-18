package org.codingtointerface.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DrawingApp {

	public static void main(String[] args) {
		/* Here Drawing class will not know which shape it is drawing, it just calls shape interface, so if new shapes comes 
		   into picture in future, just we need add bean configuration details in xml file and add new shape class and just
		   extend the shape interface in that new class, and call the bean here to draw that newely added shape.
		*/
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		Shape shapeCircle = (Shape) context.getBean("circle");
		shapeCircle.Draw();
		
		System.out.println();
		
		Shape shapeTriangle = (Shape) context.getBean("triangle");
		shapeTriangle.Draw();
		
	}

}
