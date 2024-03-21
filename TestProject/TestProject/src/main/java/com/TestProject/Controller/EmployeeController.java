package com.TestProject.Controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TestProject.Model.Employee;
import com.TestProject.Repository.EmployeeRepository;

@RestController
//@RequestMapping("/api/employees")
public class EmployeeController {
	@Autowired
	private EmployeeRepository repository;
	
	@GetMapping("/getAll")
	public List<Employee> getAll()
	{
		return repository.findAll();
	}
	
	@PostMapping("/insert")
	public ResponseEntity<Employee> postEmployee(@RequestBody Employee e)
	{
		Employee emp=repository.save(e);
		return ResponseEntity.ok(emp);
	}
	
	@GetMapping("/getOne")
	public ResponseEntity<Employee> getById(@RequestParam Integer id)
	{
		Optional<Employee> op=repository.findById(id);
		return op.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteEmployee(@RequestParam Integer id)
	{
		
		if(repository.existsById(id))
		{
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
			
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	

	@PutMapping("/update")
	public ResponseEntity<Employee> updateEmp(@RequestParam Integer id,@RequestBody Employee updateEmployee)
	{
		Optional<Employee> existingEmp=repository.findById(id);
		if(existingEmp.isPresent())
		{
			Employee empToUpdate=existingEmp.get();
			empToUpdate.setName(updateEmployee.getName());
			empToUpdate.setAddress(updateEmployee.getAddress());
			empToUpdate.setPhoneNumber(updateEmployee.getPhoneNumber());
			Employee savedEmployee=repository.save(empToUpdate);
			return ResponseEntity.ok(savedEmployee);
			
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
