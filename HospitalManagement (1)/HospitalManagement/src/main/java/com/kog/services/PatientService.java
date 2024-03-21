package com.kog.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kog.beans.Patient;
import com.kog.repositories.PatientRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PatientService {
	
	@Autowired
	private PatientRepository patientRepo;
	
		
		//Add a patient
		public Patient addPatient(Patient patient)
		{
			log.info("Add Patient of service");
			return patientRepo.save(patient);
		}
		
		//Find By Id
		public Patient getById(Integer Id)
		{
			log.info("Get By Id method of service");
			Optional<Patient> patient=patientRepo.findById(Id);
			if(patient.isPresent())
			{
				return patient.get();
			}
			else
			{
				return null;
			}
		}
		
		//Get the patients by name
		public List<Patient> getByName(String name)
		{
			log.info("get by name method of service");
			return patientRepo.findByPatientName(name);
		}
		
		//Get all the patients
		public List<Patient> getAllPatientss()
		{
			log.info("Get all method of service");
			return patientRepo.findAll();
		}
		
		//Update patient
		public Patient updatePatient(Integer id,Long phoneNumber)
		{
			log.info("Update patient method of service");
			Patient existPatient=getById(id);
			if(existPatient!=null)
			{
				existPatient.setPatientPhoneNumber(phoneNumber);
				patientRepo.save(existPatient);
				return existPatient;
			}
			else
			{
				return null;
			}
			
		}
		
		//Delete a patient 
		public String deletePatient(Integer id)
		{
			log.info("delete method of service");
			Patient existPatient=getById(id);
			if(existPatient!=null)
			{
				patientRepo.delete(existPatient);
				return "Patient with patientId "+ id +" got deleted.";
			}
			else
			{
				return "Patient with patientId "+id+" is not found.";
			}
			
		}
		
		//Find by email
//		public Patient findByPatientEmail(String email)
//		{
//			log.info("find by email method of service");
//			return patientRepo.findByPatientEmail(email);
//		}
		
}
	