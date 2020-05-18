package com.springboot.withmongodatabase.example.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.springboot.withmongodatabase.example.model.Book;
import com.springboot.withmongodatabase.example.repository.BookRepository;


@Service
public class BookService {
	
	private static final Log log = LogFactory.getLog(BookService.class);

	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public Book AddBooks(Book book) {
		try {
			Book response = mongoTemplate.insert(book);
			return response;
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}
	
	public List<Book> getAllBooks() {
		return bookRepo.findAll();
	}
	
	public Optional<Book> getBookById(String id) {
		
		return bookRepo.findById(id);
	}
	
	public int updateBookDetails(String id, Book book) {
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			Map<String,Object> props = mapper.convertValue(book, Map.class);
			props.values().removeIf(Objects::isNull);
			
		    Update update = new Update();
		    props.forEach(update::set);
		    Query query = new Query();
		    query.addCriteria(Criteria.where("_id").is(id));
			Book books = mongoTemplate.findAndModify(query, update, Book.class);
			if (books == null) {
				return 0;
			}
			return 1;
		} catch(Exception e) {
			log.error(e.getMessage(), e);
			return 0;
		}
	}
	
	public int DeleteBook(String id) {
		try {
			bookRepo.deleteById(id);
			return 1;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return 0;
		}
	}
}
