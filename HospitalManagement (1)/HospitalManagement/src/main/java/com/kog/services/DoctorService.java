package com.kog.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kog.beans.Doctor;
import com.kog.repositories.DoctorRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DoctorService {
	
	@Autowired
	private DoctorRepository doctRepo;
	
	//Add a doctor
	public Doctor addDoctor(Doctor doctor)
	{
		log.info("Add Doctor of service");
		return doctRepo.save(doctor);
	}
	
	//Find By Id
	public Doctor getById(Integer Id)
	{
		log.info("Get By Id method of service");
		Optional<Doctor> doctor=doctRepo.findById(Id);
		if(doctor.isPresent())
		{
			return doctor.get();
		}
		else
		{
			return null;
		}
	}
	
	//Get the doctors by name
	public List<Doctor> getByName(String name)
	{
		log.info("get by name method of service");
		return doctRepo.findByDoctorName(name);
	}
	
	//Get all the doctors
	public List<Doctor> getAllDoctors()
	{
		log.info("Get all method of service");
		return doctRepo.findAll();
	}
	
	//Update doctor
	public Doctor updateDoctor(Integer id,Long phoneNumber)
	{
		log.info("Update doctor method of service");
		Doctor existDoc=getById(id);
		if(existDoc!=null)
		{
			existDoc.setDoctorPhoneNumber(phoneNumber);
			doctRepo.save(existDoc);
			return existDoc;
		}
		else
		{
			return null;
		}
		
	}
	
	//Delete a doctor 
	public String deleteDoctor(Integer id)
	{
		log.info("delete method of service");
		Doctor existDoc=getById(id);
		if(existDoc!=null)
		{
			doctRepo.delete(existDoc);
			return "Doctor with doctorId "+ id +" got deleted.";
		}
		else
		{
			return "Doctor with doctorId "+id+" is not found.";
		}
		
	}
	
	//Find by email
//	public Doctor findByDoctorEmail(String email)
//	{
//		log.info("find by email method of service");
//		return doctRepo.findByDoctorEmail(email);
//	}
	
}
