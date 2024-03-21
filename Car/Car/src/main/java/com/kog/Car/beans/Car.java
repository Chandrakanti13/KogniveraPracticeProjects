package com.kog.Car.beans;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="tables")
@Data
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int regNo;
	private String brand;
	private String model;
	private BigDecimal price;

}
