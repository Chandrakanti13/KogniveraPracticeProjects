package com.kog.SpringBootBook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kog.SpringBootBook.beans.Book;
import com.kog.SpringBootBook.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	
	//Getting all the books by using findAll() of CrudRepository
	public List<Book> getAllBooks()
	{
		List<Book> books=new ArrayList<Book>();
		bookRepository.findAll().forEach(book->books.add(book));
		return books;
	}
	
	//Getting a specific book by using the findById() of CrudRepository
	public Book getBookById(int id)
	{
		return bookRepository.findById(id).get();
	}
	
	
	//Saving a specific book by using the save() of CrudRepository
	public void saveOrUpdate(Book book)
	{
		bookRepository.save(book);
	}
	
	//Deleting a specific book by using deleteById() of CrudRepository
	public void delete(int id)
	{
		bookRepository.deleteById(id);
	}
	
	//Updating a book 
	public void update(Book book,int bookId)
	{
		bookRepository.save(book);
	}
}
