package com.kog.SpringBootBook.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kog.SpringBootBook.beans.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer>{
	

}
