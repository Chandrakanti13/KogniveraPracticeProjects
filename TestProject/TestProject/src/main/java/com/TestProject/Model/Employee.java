package com.TestProject.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Document(collection ="project")
public class Employee {
	
	private Integer id;
	private String name;
	private String address;
    private Long phoneNumber;

	

}
