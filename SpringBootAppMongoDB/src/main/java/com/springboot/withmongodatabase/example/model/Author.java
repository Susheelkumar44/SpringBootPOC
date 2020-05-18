package com.springboot.withmongodatabase.example.model;

import org.springframework.data.mongodb.core.mapping.Field;

public class Author {

	//@Field("authorName")
	private String authorName;
	//@Field("authorGrade")
	private String authorGrade;
	
	public Author(String authorName, String authorGrade) {
		super();
		this.authorName = authorName;
		this.authorGrade = authorGrade;
	}
	public String getauthorName() {
		return authorName;
	}
	public void setauthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getauthorGrade() {
		return authorGrade;
	}
	public void setauthorGrade(String authorGrade) {
		this.authorGrade = authorGrade;
	}
}
