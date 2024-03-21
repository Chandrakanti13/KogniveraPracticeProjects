package com.kog.LibraryManagementSystem.beans;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class User {
	@Id
	private Integer user_id;
	private String user_name;

}
