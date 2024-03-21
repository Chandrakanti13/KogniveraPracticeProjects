package com.kog.SpringBootBook.controller;

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

import com.kog.SpringBootBook.beans.Book;
import com.kog.SpringBootBook.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {
	
	@Autowired
    BookService bookService;
	
	
	//Creating get mapping which retrieves all the records
	@GetMapping("/books")
	private List<Book> getAllBooks()
	{
		return bookService.getAllBooks();
	}
	
	
	//Creating a get mapping which retrieves the details of  a speicific book
	@GetMapping("/book/{bookId}")
	private Book getBook(@PathVariable("bookId") int bookId)
	{
		return bookService.getBookById(bookId);
	}
	
	//Creating a post mapping that post the book detail in the database
	@PostMapping("/book")
	private int insertBook(@RequestBody Book book )
	{
		bookService.saveOrUpdate(book);
		return book.getBookId();
	}
	
	//Creating a delete mapping that deletes a specific book
	@DeleteMapping("/book/{bookId}")
	private void deleteBook(@PathVariable("bookId") int bookId)
	{
		bookService.delete(bookId);
	}
	
	//Creating a put mapping that updates the book details
	@PutMapping("/updateBook/{bookId}")
	private Book updateBook(@RequestBody Book book,@PathVariable("bookId") int bookId)
	{
		bookService.update(book, bookId);
		return book;
	}

}
