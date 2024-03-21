package com.kog.LibraryManagementSystem.beans;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Book {
	@Id
	private Integer book_id;
	private String book_name;
	private String book_author;

}
