package com.kog.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "doctors")
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
	
	@Id
	private Integer doctorId;
	private String doctorName;
	private String doctorEmail;
	private Long  doctorPhoneNumber;
	

}
