package com.kog.StudentProject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kog.StudentProject.beans.Student;
import com.kog.StudentProject.repository.StudentRepository;



@RestController
@RequestMapping("/api/student")
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	//Insert Student
	@PostMapping
	public ResponseEntity<Student> addStudent(@RequestBody Student student)
	{
		Student savedStudent=studentRepository.save(student);
		return ResponseEntity.ok(savedStudent);
	}
	
	//Get all Student
	@GetMapping("/getAll")
	public List<Student> getAllStudents()
	{
		return studentRepository.findAll();
	}
	
	//Get A Student By Id
	@GetMapping("/id/{id}")
	public ResponseEntity<Student> getById(@PathVariable("id") Long id)
	{
		Optional<Student> student=studentRepository.findById(id);
		return student.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
	}
	
	//Get Students By Name
	@GetMapping("/name/{name}")
	public List<Student> getByName(@PathVariable("name") String name)
	{
		return studentRepository.getByName(name);
	}
	
	//Update a Student Based on Id
	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id,@RequestBody Student updateStudent )
	{
		Optional<Student> existingStudent=studentRepository.findById(id);
		if(existingStudent.isPresent())
		{
			Student studentToUpdate=existingStudent.get();
			studentToUpdate.setName(updateStudent.getName());
			studentToUpdate.setAddress(updateStudent.getAddress());
			Student savedStudent=studentRepository.save(studentToUpdate);
			return ResponseEntity.ok(savedStudent);
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}
	
	
	//Delete a Student Based on Id
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable Long id)
	{
		if(studentRepository.existsById(id))
		{
			studentRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}

}
