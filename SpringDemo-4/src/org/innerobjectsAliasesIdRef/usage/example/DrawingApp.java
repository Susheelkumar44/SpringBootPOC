package org.innerobjectsAliasesIdRef.usage.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DrawingApp {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		//Usage of aliases
		Triangle triangle = (Triangle) context.getBean("triangle-alias");
		triangle.Draw();

	}

}
