package com.kog.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


import com.kog.beans.Patient;

@Repository
public interface PatientRepository extends MongoRepository<Patient, Integer>{
	
	@Query(value="{'patientName':?0}",fields="{'patientId':0,'patientEmail':0}")
	List<Patient> findByPatientName(String patientName);
	
//	@Query(value="{'patientEmail':?0}, 'patientName':{$exists:true}}",fields= "{'patientPhoneNumber':1,'patientAge':1}")
//	Patient findByPatientEmail(String patientEmail);

}
