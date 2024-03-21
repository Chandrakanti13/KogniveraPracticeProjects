package com.kog.MedicineShop.Controller;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kog.MedicineShop.Model.Medicine;

import com.kog.MedicineShop.Service.MedicineService;


@SpringBootTest
@AutoConfigureMockMvc

public class MedicineControllerTest {
	
	@MockBean
	MedicineService medService;
	
	@Autowired
	MockMvc mockMvc;
	
	ObjectMapper objectMapper=new ObjectMapper();
	
	
	@Test
	public void testGetAllMedicines() throws Exception
	{
			Medicine  med1=new Medicine(1,"k","o","0","9",1l);
			Medicine  med=new Medicine(2,"k","o","0","9",1l);
			
			List<Medicine> medicines=new ArrayList<Medicine>();
			medicines.add(med1);
			medicines.add(med);
			
			when(medService.getAllMedicines()).thenReturn(medicines);
			mockMvc.perform(MockMvcRequestBuilders.get("/api/getAll")).andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].medicineRegNo").value(1))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].type").value("k"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].medicineName").value("o"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].manufactureDate").value("0"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].expiryDate").value("9"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].stock").value(1l))
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].medicineRegNo").value(2))
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].type").value("k"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].medicineName").value("o"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].manufactureDate").value("0"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].expiryDate").value("9"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].stock").value(1l));
			verify(medService, times(1)).getAllMedicines();
			
			
	}
	
	
	@Test
		void testAddProduct() throws Exception {
			Medicine med = new Medicine(1,"Allo","dolo","09-09-23","09-12-34",1l);
	 
			String medicineJson = objectMapper.writeValueAsString(med);
	 
			when(medService.addMedicine(med)).thenReturn(med);
	 
			mockMvc.perform(
					MockMvcRequestBuilders.post("/api/insert")
					.contentType(MediaType.APPLICATION_JSON)
					.content(medicineJson))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$.medicineRegNo").value(1))
					.andExpect(MockMvcResultMatchers.jsonPath("$.type").value("Allo"))
					.andExpect(MockMvcResultMatchers.jsonPath("$.medicineName").value("dolo"))
					.andExpect(MockMvcResultMatchers.jsonPath("$.manufactureDate").value("09-09-23"))
					.andExpect(MockMvcResultMatchers.jsonPath("$.expiryDate").value("09-12-34"))
			        .andExpect(MockMvcResultMatchers.jsonPath("$.stock").value(1l));
	 
			verify(medService, times(1)).addMedicine(med);
		}
	
	@Test
		void testGetAProduct() throws Exception {
		Medicine med = new Medicine(1,"Allo","dolo","09-09-23","09-12-34",1l);
	 
			when(medService.getAMedicine(1)).thenReturn(med);
	 
			mockMvc.perform(MockMvcRequestBuilders.get("/api/get/{regNo}", 1))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.jsonPath("$.medicineRegNo").value(1))
					.andExpect(MockMvcResultMatchers.jsonPath("$.type").value("Allo"))
					.andExpect(MockMvcResultMatchers.jsonPath("$.medicineName").value("dolo"))
					.andExpect(MockMvcResultMatchers.jsonPath("$.manufactureDate").value("09-09-23"))
					.andExpect(MockMvcResultMatchers.jsonPath("$.expiryDate").value("09-12-34"))
			        .andExpect(MockMvcResultMatchers.jsonPath("$.stock").value(1l));
					
	 
			verify(medService, times(1)).getAMedicine(1);
		}
	

	 
	
	@Test
		void testUpdateMedicine() throws Exception {
	 
		Medicine med = new Medicine(1,"Allo","dolo","09-09-23","09-12-34",1l);
			
			String updatedMedicineJson = objectMapper.writeValueAsString(med);
			when(medService.updateMedicine(1, med)).thenReturn(med);
	 
			mockMvc.perform(MockMvcRequestBuilders.put("/api/update/{regNo}", 1)
					.contentType(MediaType.APPLICATION_JSON)
		            .content(updatedMedicineJson))
			
					.andExpect(MockMvcResultMatchers.status().isOk());	 
			verify(medService, times(1)).updateMedicine(1,med);
	 
			
		}
	
	
	@Test
		void testDeleteProduct() throws Exception {
		Medicine med = new Medicine(1,"Allo","dolo","09-09-23","09-12-34",1l);
			
			when(medService.deleteMedicine(med.getMedicineRegNo())).thenReturn("Medicine removed");
			
			mockMvc.perform(MockMvcRequestBuilders.delete("/api/delete/{id}",1).contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("Medicine removed"));
	 
			
			verify(medService, times(1)).deleteMedicine(1);
		}
}

