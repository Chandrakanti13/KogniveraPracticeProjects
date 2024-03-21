package com.kog.SpringBootBook.beans;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="books")
public class Book {
	@Id
	private int bookId;
	private String bookName;
	private String bookAuthor;
	private BigDecimal bookPrice;

}
