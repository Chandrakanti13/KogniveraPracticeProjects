package com.kog.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "patients")
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
	
	@Id
	private Integer patientId;
	private String patientName;
	private Integer patientAge;
	private Long patientPhoneNumber;
	private String patientEmail;

}
