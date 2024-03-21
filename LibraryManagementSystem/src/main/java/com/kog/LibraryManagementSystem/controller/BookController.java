package com.kog.LibraryManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kog.LibraryManagementSystem.beans.Book;
import com.kog.LibraryManagementSystem.service.BookService;



@RestController
@RequestMapping("/api/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/getAllBooks")
	public List<Book> getAllBooks()
	{
		return bookService.getAllBooks();
	}
	
	@PostMapping
	public Book addBook(@RequestBody Book b)
	{
		return bookService.addBook(b);
	}
	
	@GetMapping("/{id}")
	public Book getSingleBook(@PathVariable("id") Integer id)
	{
		return bookService.getBook(id);
	}
	
	@PutMapping("/{id}/{name}/{author}")
	public Book updateBook(@PathVariable("id") Integer id,@PathVariable("name") String name,@PathVariable("author") String author)
	{
		return bookService.updateBook(id, name, author);
	}
	
	@DeleteMapping("/{id}")
	public String deleteBook(@PathVariable("id") Integer id)
	{
		return bookService.deleteBook(id);
	}

}
