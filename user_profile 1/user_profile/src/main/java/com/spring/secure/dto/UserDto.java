package com.spring.secure.dto;

import lombok.Data;

@Data
public class UserDto {
	
	private String email;
	private String password;
	private String role;
	private String fullname;
	
	public UserDto(String email, String password, String role, String fullname) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
		this.fullname = fullname;
	}

	
	
	

}