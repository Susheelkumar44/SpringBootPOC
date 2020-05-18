package com.springboot.withmongodatabase.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springboot.withmongodatabase.example.model.Book;

public interface BookRepository extends MongoRepository<Book, String> {

}
