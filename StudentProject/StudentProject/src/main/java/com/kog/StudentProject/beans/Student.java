package com.kog.StudentProject.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "students")
public class Student {
	@Id
	private Long id;
	private String name;
	private String address;

}
