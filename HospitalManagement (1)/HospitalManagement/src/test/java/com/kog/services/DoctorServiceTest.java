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


import com.kog.beans.Doctor;
import com.kog.repositories.DoctorRepository;

@ExtendWith(MockitoExtension.class)
class DoctorServiceTest {
	
	@InjectMocks
	private DoctorService doctorServ;
	@Mock
	private DoctorRepository doctRepo;
	
	@BeforeEach
	public void init()
	{
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testAddDoctor() {
		Doctor doctor=new Doctor(1,"Chandra","chnadra@gmail.com",9900776655l);
		when(doctRepo.save(doctor)).thenReturn(doctor);
		Doctor savedDoctor=doctorServ.addDoctor(doctor);
		verify(doctRepo,times(1)).save(savedDoctor);
		assertEquals(doctor, savedDoctor);
	}

	@Test
	void testGetById() {
		Optional<Doctor> existDoc=Optional.of((new Doctor(1,"Chandra","chnadra@gmail.com",99007766551l)));
	    when(doctRepo.findById(1)).thenReturn(existDoc);
	    Optional<Doctor> actualDoc=Optional.of(doctorServ.getById(1));
	    assertEquals(existDoc, actualDoc);
	}
     
	@Disabled
	@Test
	void testGetByName() {
		
	}

	@Test
	void testGetAllDoctors() {
		
		Doctor doctor1=new Doctor(1,"Chandra","chnadra@gmail.com",9900776655l);
		Doctor doctor2=new Doctor(2,"Chandra2","chnadra2@gmail.com",9920776655l);
		List<Doctor> doctors=new ArrayList<Doctor>();
		doctors.add(doctor1);
		doctors.add(doctor2);
		
		when(doctRepo.findAll()).thenReturn(doctors);
		List<Doctor> docList=doctorServ.getAllDoctors();
		
		assertEquals(2, docList.size());
		verify(	doctRepo,times(1)).findAll();
		
	}

	@Test
	void testUpdateDoctor() {
		Doctor doct=new Doctor(1,"Chandra","chnadra@gmail.com",9900776655l);
		when(doctRepo.findById(doct.getDoctorId())).thenReturn(Optional.of(doct));
		when(doctRepo.save(Mockito.any(	Doctor.class))).thenReturn(doct);
		Doctor doc1=doctorServ.updateDoctor(1, doct.getDoctorPhoneNumber());
		assertEquals(doct, doc1);
	}

	@Test
	void testDeleteDoctor() {
		Doctor doct=new Doctor(1,"Chandra","chnadra@gmail.com",9900776655l);
		when(doctRepo.findById(doct.getDoctorId())).thenReturn(Optional.of(doct));
	    String result=doctorServ.deleteDoctor(doct.getDoctorId());
	    verify(doctRepo,times(1)).delete(doct);
	    assertEquals("Doctor with doctorId "+doct.getDoctorId()+" got deleted.", result);
	}

	@Disabled
	@Test
	void testFindByDoctorEmail() {
		
	}

}
