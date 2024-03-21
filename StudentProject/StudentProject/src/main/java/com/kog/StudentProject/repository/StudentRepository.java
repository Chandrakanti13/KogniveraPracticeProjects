package com.kog.StudentProject.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.kog.StudentProject.beans.Student;
@Repository
public interface StudentRepository extends MongoRepository<Student, Long>{
	@Query("{'name':?0}")
	List<Student> getByName(String name);

}
