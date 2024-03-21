package com.kog.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kog.beans.Doctor;
import com.kog.services.DoctorService;

@SpringBootTest
@AutoConfigureMockMvc
class DoctorControllerTest {
	
	@MockBean
	DoctorService docService;
	
	@Autowired
	MockMvc mockMvc;
	
	ObjectMapper objectMapper=new ObjectMapper();

	@Disabled
	@Test
	void testLogin() {
		
	}

	@WithMockUser(username = "doctor", roles = {"ADMIN"} , password="doctor123")
	@Test
	void testAddDoctor() throws Exception{
		 
        Doctor doctor=new Doctor(1,"Chandrakanti","chandra@gmail.com",9911667788l);
		
		String doctorJson = objectMapper.writeValueAsString(doctor);
		 
		when(docService.addDoctor(doctor)).thenReturn(doctor);
 
		mockMvc.perform(
				MockMvcRequestBuilders.post("/admin/insertDoctor")
				.contentType(MediaType.APPLICATION_JSON)
				.content(doctorJson))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.doctorId").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.doctorName").value("Chandrakanti"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.doctorEmail").value("chandra@gmail.com"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.doctorPhoneNumber").value(9911667788l));
 
		verify(docService, times(1)).addDoctor(doctor);
	}
	

	@WithMockUser(username = "doctor", roles = {"ADMIN"} , password="doctor123")
	@Test
	void testGetById() throws Exception{
		
		Doctor doctor=new Doctor(1,"Chandrakanti","chandra@gmail.com",9911667788l);
		
		when(docService.getById(1)).thenReturn(doctor);
		 
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/doctorId/{id}", 1))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.doctorId").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.doctorName").value("Chandrakanti"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.doctorEmail").value("chandra@gmail.com"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.doctorPhoneNumber").value(9911667788l));
				
		verify(docService, times(1)).getById(1);
		
	}

	@Disabled
	@Test
	void testGetByName() {
		
	}

	@WithMockUser(username = "doctor", roles = {"ADMIN"} , password="doctor123")
	@Test
	void testGetAll() throws Exception{
		Doctor doctor1=new Doctor(1,"Chandrakanti","chandra@gmail.com",9911667788l);
		Doctor doctor2=new Doctor(2,"Chandra","chandrakanti@gmail.com",9911667788l);
		List<Doctor> doctors=new ArrayList<Doctor>();
		doctors.add(doctor1);
		doctors.add(doctor2);
		
		when(docService.getAllDoctors()).thenReturn(doctors);
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/doctorAll")).andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].doctorId").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].doctorName").value("Chandrakanti"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].doctorEmail").value("chandra@gmail.com"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].doctorPhoneNumber").value(9911667788l))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].doctorId").value(2))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].doctorName").value("Chandra"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].doctorEmail").value("chandrakanti@gmail.com"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].doctorPhoneNumber").value(9911667788L));
		verify(docService, times(1)).getAllDoctors();
	}
	
	@WithMockUser(username = "doctor", roles = {"ADMIN"} , password="doctor123")
	@Test
	void testUpdateDoctor() throws Exception{
		
		Doctor doctor=new Doctor(1,"Chandrakanti","chandra@gmail.com",9911667788L);
		
		String updatedDoctor = objectMapper.writeValueAsString(doctor);
		
		when(docService.updateDoctor(1, 9911667788l)).thenReturn(doctor);
		 
		mockMvc.perform(MockMvcRequestBuilders.put("/admin/updateDoctor/{id}", 1)
				.param("phoneNumber", "9911667788")
				.contentType(MediaType.APPLICATION_JSON)
	            .content(updatedDoctor))
		
				.andExpect(MockMvcResultMatchers.status().isOk());	 
		verify(docService, times(1)).updateDoctor(1, 9911667788L);	
		
		
	}

	
	@WithMockUser(username = "doctor", roles = {"ADMIN"} , password="doctor123")
	@Test
	void testDeleteDoctor() throws Exception{
		Doctor doctor=new Doctor(1,"Chandrakanti","chandra@gmail.com",9911667788l);
		when(docService.deleteDoctor(doctor.getDoctorId())).thenReturn("Doctor with doctorId "+ doctor.getDoctorId() +" got deleted.");
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/admin/deleteDoctor/{id}",1).contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string("Doctor with doctorId "+doctor.getDoctorId()+" got deleted."));
 
		verify(docService, times(1)).deleteDoctor(1);
		
	}

	@Disabled
	@Test
	void testGetByEmail() {
		
	}

	@Disabled
	@Test
	void testLogout() {
		
	}

}
