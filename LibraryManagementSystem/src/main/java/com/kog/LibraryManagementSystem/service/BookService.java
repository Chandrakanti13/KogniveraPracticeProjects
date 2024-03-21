package com.kog.LibraryManagementSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kog.LibraryManagementSystem.beans.Book;
@Repository
public class BookService {

	private List<Book> bookList=new ArrayList<Book>();
	
	//Get all The Books
		public List<Book> getAllBooks()
		{
			return bookList;
		}
		
	//Add Books
		public Book addBook(Book b)
		{
			 bookList.add(b);
			 return b;
		}
		
	//Get a Book
		public Book getBook(Integer id)
		{
			return bookList.stream().filter(u->u.getBook_id().equals(id)).findFirst().orElse(null);
		}
		
	//Update a Book
		public Book updateBook(Integer id,String name,String  author)
		{
			Book b=bookList.stream().filter(book->book.getBook_id().equals(id)).findFirst().orElse(null);
			
			if(b!=null)
			{
				b.setBook_name(name);
				b.setBook_author(author);
				return b;
			}
			return null;
				
		}
		
	//Delete Book
		public String deleteBook(Integer id)
		{
			bookList.removeIf(b->b.getBook_id().equals(id));
			return "Book got deleted";
			
		}	
}
