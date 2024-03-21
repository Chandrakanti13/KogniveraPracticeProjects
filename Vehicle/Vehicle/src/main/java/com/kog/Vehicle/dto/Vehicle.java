package com.kog.Vehicle.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "vehicles")
public class Vehicle {
	
	@Id
	private Integer regNo;
	private String model;
	private String color;

}
