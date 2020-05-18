package com.springboot.withmongodatabase.example.model;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Book")
public class Book {
	
	@Id
	private String id;
	private String bookName;
	private String bookGrade;
	private Author author;
	
	public Book(String id, String bookName, String bookGrade,Author author) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.bookGrade=bookGrade;
		this.author = author;
	}
	
	public Book() {
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public String getBookGrade() {
		return bookGrade;
	}

	public void setBookGrade(String bookGrade) {
		this.bookGrade = bookGrade;
	}

	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	
	
	
}
