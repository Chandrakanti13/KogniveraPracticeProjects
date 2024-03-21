package com.CustomerProject.Beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Document(collection = "customers")
@AllArgsConstructor
public class Customer {
	@Id
	private Integer customerId;
	private String customerName;
	private String customerEmail;
	private String customerGender;
	private String customerAddress;

}
