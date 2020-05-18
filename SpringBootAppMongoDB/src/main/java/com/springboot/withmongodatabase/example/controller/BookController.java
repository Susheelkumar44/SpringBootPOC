package com.springboot.withmongodatabase.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.json.simple.JSONObject;
import com.springboot.withmongodatabase.example.model.Book;
import com.springboot.withmongodatabase.example.service.BookService;

@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	JSONObject responseObj = new JSONObject();
	HttpHeaders responseHeaders = new HttpHeaders();
	
	
	@SuppressWarnings("unchecked")
	@PostMapping("/books")
	public JSONObject savebook(@RequestBody Book book) {
		Book returnedResp = bookService.AddBooks(book);
		if (returnedResp == null) {
			return this.ResponseGenerator(500, HttpStatus.INTERNAL_SERVER_ERROR, 
					"Error in inserting book details in database");
		}
		responseObj.put("code", 201);
		responseObj.put("type", HttpStatus.CREATED);
		responseObj.put("message", "Successfully Created");
		responseObj.put("bookID", book.getId());
		return responseObj;
	}
	
	@RequestMapping("/books")
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}
	
	@RequestMapping("/books/{id}")
	public Optional<Book> getBookById(@PathVariable String id) {
		return bookService.getBookById(id);
	}
	
	
	@PutMapping("/books/{id}")
	public JSONObject update(@PathVariable String id, @RequestBody Book book) {
		if (!this.getBookById(id).isPresent()) {
			responseHeaders.set("Status", "404 NOT_FOUND");
			return this.ResponseGenerator(404, HttpStatus.NOT_FOUND, "Invalid Book Details");
		}
		int updateResp = bookService.updateBookDetails(id, book);
		System.out.println("updateResp "+updateResp);
		if (updateResp == 0) {
			return this.ResponseGenerator(500, HttpStatus.INTERNAL_SERVER_ERROR, 
					"Error in updating book details in database");
		}
		return this.ResponseGenerator(200, HttpStatus.OK, "Successfull");
	}
	
	@DeleteMapping("/books/{id}")
	public JSONObject delete(@PathVariable String id) {
		if (!this.getBookById(id).isPresent()) {
			responseHeaders.set("Status", "404 NOT_FOUND");
			return this.ResponseGenerator(404, HttpStatus.NOT_FOUND, "Invalid Book Details");
		}
		int returnedResp = bookService.DeleteBook(id);
		if (returnedResp == 0) {
			return this.ResponseGenerator(500, HttpStatus.INTERNAL_SERVER_ERROR, 
					"Error in deleteing book details in database");
		}
		System.out.println("here");
		return this.ResponseGenerator(200, HttpStatus.OK, "Successfull");
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject ResponseGenerator(int code, HttpStatus type, String message) {
		responseObj.put("code", code);
		responseObj.put("type", type);
		responseObj.put("message", message);
		return responseObj;
	}


}
