package com.kog.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.kog.beans.Doctor;



@Repository
public interface DoctorRepository extends MongoRepository<Doctor, Integer>{
	
	@Query(value="{'doctorName':?0}",fields="{'doctorPhoneNumber':1,'doctorEmail':1}")
	List<Doctor> findByDoctorName(String doctorName);
	
//	@Query(value="{'doctorEmail':?0}, 'doctorName':{$exists:true}}",fields= "{'doctorPhoneNumber':1}")
//	Doctor findByDoctorEmail(String email);
	
 
}
