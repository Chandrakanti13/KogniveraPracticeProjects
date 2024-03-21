package com.kog.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import com.kog.beans.Patient;
import com.kog.repositories.PatientRepository;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

	
	@InjectMocks
	private PatientService patientServ;
	@Mock
	private PatientRepository patientRepo;
	
	@BeforeEach
	public void init()
	{
		MockitoAnnotations.openMocks(this);
	}
	@Test
	void testAddPatient() {
		
		Patient patient=new Patient(1,"Chandra",23,9900776655l,"chnadra@gmail.com");
		when(patientRepo.save(patient)).thenReturn(patient);
		Patient savedPatient=patientServ.addPatient(patient);
		verify(patientRepo,times(1)).save(savedPatient);
		assertEquals(patient, savedPatient);
		
	}

	@Test
	void testGetById() {
		
		Optional<Patient> existPatient=Optional.of((new Patient(1,"Chandra",23,99007766551l,"chnadra@gmail.com")));
	    when(patientRepo.findById(1)).thenReturn(existPatient);
	    Optional<Patient> actualPatient=Optional.of(patientServ.getById(1));
	    assertEquals(existPatient, actualPatient);
		
	}

	@Disabled
	@Test
	void testGetByName() {
		
	}

	@Test
	void testGetAllPatientss() {
		
		Patient patient1=new Patient(1,"Chandra",23,9900776655l,"chnadra@gmail.com");
		Patient patient2=new Patient(2,"Chandra2",23,9920776655l,"chnadra2@gmail.com");
		List<Patient> patients=new ArrayList<Patient>();
		patients.add(patient1);
		patients.add(patient2);
		
		when(patientRepo.findAll()).thenReturn(patients);
		List<Patient> patientList=patientServ.getAllPatientss();
		
		assertEquals(2, patientList.size());
		verify(	patientRepo,times(1)).findAll();
		
	}

	@Test
	void testUpdatePatient() {
		
		Patient patient=new Patient(1,"Chandra",23,9900776655l,"chnadra@gmail.com");
		when(patientRepo.findById(patient.getPatientId())).thenReturn(Optional.of(patient));
		when(patientRepo.save(Mockito.any(	Patient.class))).thenReturn(patient);
		Patient patient11=patientServ.updatePatient(1, patient.getPatientPhoneNumber());
		assertEquals(patient, patient11);
		
	}

	
	@Test
	void testDeletePatient() {
		
		Patient patient=new Patient(1,"Chandra",23,9900776655l,"chnadra@gmail.com");
		when(patientRepo.findById(patient.getPatientId())).thenReturn(Optional.of(patient));
	    String result=patientServ.deletePatient(patient.getPatientId());
	    verify(patientRepo,times(1)).delete(patient);
	    assertEquals("Patient with patientId "+patient.getPatientId()+" got deleted.", result);
	
		
	}

	@Disabled
	@Test
	void testFindByPatientEmail() {
		
	}

}
