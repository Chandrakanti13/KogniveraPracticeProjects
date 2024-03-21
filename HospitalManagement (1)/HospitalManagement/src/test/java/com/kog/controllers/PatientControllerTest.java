package com.kog.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import com.kog.beans.Patient;
import com.kog.services.PatientService;


@SpringBootTest
@AutoConfigureMockMvc
class PatientControllerTest {
	
	@MockBean
	PatientService patientService;
	
	@Autowired
	MockMvc mockMvc;
	
	ObjectMapper objectMapper=new ObjectMapper();

	@Disabled
	@Test
	void testLogin() {
		
	}

	
	@WithMockUser(username = "patient", roles = {"ADMIN","USER"} , password="patient123")
	@Test
	void testAddPatient() throws Exception{
		
       Patient patient=new Patient(1,"Chandrakanti",23,9911667788l,"chandra@gmail.com");
		
		String patientJson = objectMapper.writeValueAsString(patient);
		 
		when(patientService.addPatient(patient)).thenReturn(patient);
 
		mockMvc.perform(
				MockMvcRequestBuilders.post("/user/insertPatient")
				.contentType(MediaType.APPLICATION_JSON)
				.content(patientJson))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.patientId").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.patientName").value("Chandrakanti"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.patientAge").value(23))
				.andExpect(MockMvcResultMatchers.jsonPath("$.patientPhoneNumber").value(9911667788l))
				.andExpect(MockMvcResultMatchers.jsonPath("$.patientEmail").value("chandra@gmail.com"));
 
		verify(patientService, times(1)).addPatient(patient);
		
	}

	
	@WithMockUser(username = "patient", roles = {"ADMIN","USER"} , password="patient123")
	@Test
	void testGetById() throws Exception{
		

		Patient patient=new Patient(1,"Chandrakanti",23,9911667788l,"chandra@gmail.com");
		when(patientService.getById(1)).thenReturn(patient);
		 
		mockMvc.perform(MockMvcRequestBuilders.get("/user/patientId/{id}", 1))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.patientId").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.patientName").value("Chandrakanti"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.patientAge").value(23))
				.andExpect(MockMvcResultMatchers.jsonPath("$.patientPhoneNumber").value(9911667788l))
				.andExpect(MockMvcResultMatchers.jsonPath("$.patientEmail").value("chandra@gmail.com"));
				
		verify(patientService, times(1)).getById(1);
		
	}

	@Disabled
	@Test
	void testGetByName() {
		
	}

	
	@WithMockUser(username = "patient", roles = {"ADMIN","USER"} , password="patient123")
	@Test
	void testGetAll() throws Exception{
		
		Patient patient1=new Patient(1,"Chandrakanti",23,9911667788l,"chandra@gmail.com");
		Patient patient2=new Patient(2,"Chandra",23,9911667788l,"chandrakanti@gmail.com");
		List<Patient> patients=new ArrayList<Patient>();
		patients.add(patient1);
		patients.add(patient2);
		
		when(patientService.getAllPatientss()).thenReturn(patients);
		mockMvc.perform(MockMvcRequestBuilders.get("/user/patientAll")).andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].patientId").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].patientName").value("Chandrakanti"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].patientAge").value(23))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].patientPhoneNumber").value(9911667788l))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].patientEmail").value("chandra@gmail.com"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].patientId").value(2))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].patientName").value("Chandra"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].patientAge").value(23))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].patientPhoneNumber").value(9911667788l))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].patientEmail").value("chandrakanti@gmail.com"));
		
	}

	
	@WithMockUser(username = "patient", roles = {"ADMIN","USER"} , password="patient123")
	@Test
	void testUpdatePatient() throws Exception{
		
		
		Patient patient=new Patient(1,"Chandrakanti",23,9911667788l,"chandra@gmail.com");
		
		String updatedPatient = objectMapper.writeValueAsString(patient);
		
		when(patientService.updatePatient(1, patient.getPatientPhoneNumber())).thenReturn(patient);
		 
		mockMvc.perform(MockMvcRequestBuilders.put("/user/updatePatient/{id}", 1)
				.param("phoneNumber", "9911667788")
				.contentType(MediaType.APPLICATION_JSON)
	            .content(updatedPatient))
		
				.andExpect(MockMvcResultMatchers.status().isOk());
			

		verify(patientService, times(1)).updatePatient(1, patient.getPatientPhoneNumber());
		
	}

	
	@WithMockUser(username = "patient", roles = {"ADMIN","USER"} , password="patient123")
	@Test
	void testDeletePatient() throws Exception{
		Patient patient=new Patient(1,"Chandrakanti",23,9911667788l,"chandra@gmail.com");
		
		when(patientService.deletePatient(patient.getPatientId())).thenReturn("Patient with patientId "+ patient.getPatientId() +" got deleted.");
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/user/deletePatient/{id}",1).contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string("Patient with patientId "+patient.getPatientId()+" got deleted."));
 
		verify(patientService, times(1)).deletePatient(1);
		
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
