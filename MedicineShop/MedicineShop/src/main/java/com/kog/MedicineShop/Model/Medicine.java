package com.kog.MedicineShop.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="medicinestore")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Medicine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int medicineRegNo;
	private String type;
	private String medicineName;
	private String manufactureDate;
	private String expiryDate;
	private Long stock;
	

}
