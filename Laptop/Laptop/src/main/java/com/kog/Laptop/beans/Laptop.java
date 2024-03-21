package com.kog.Laptop.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "laptops")
public class Laptop {
	@Id
	private Integer laptopId;
	private String laptopBrand;
	private String laptopModel;
	private Long laptopPrice;
	

}
