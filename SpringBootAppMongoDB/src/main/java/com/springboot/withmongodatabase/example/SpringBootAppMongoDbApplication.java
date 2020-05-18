package com.springboot.withmongodatabase.example;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class SpringBootAppMongoDbApplication {

	private static final Log log = LogFactory.getLog(SpringBootAppMongoDbApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootAppMongoDbApplication.class, args);
	}

}
