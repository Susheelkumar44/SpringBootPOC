package org.messagesource.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DrawingApp {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		Triangle triangle = (Triangle) context.getBean("triangle");
		triangle.Draw();
		
		//To print message after reading from properties file
		System.out.println(context.getMessage("greeting", null, "default message", null)); 

	}

}
