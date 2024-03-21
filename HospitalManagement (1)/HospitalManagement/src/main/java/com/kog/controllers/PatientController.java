	
	package com.kog.controllers;

	import java.util.List;

	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
	import org.springframework.web.bind.annotation.DeleteMapping;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.PutMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.bind.annotation.RestController;

import com.kog.beans.Patient;

import com.kog.services.PatientService;

import jakarta.servlet.http.HttpServletRequest;
	import jakarta.servlet.http.HttpServletResponse;
	import lombok.extern.slf4j.Slf4j;

	@RestController
	@RequestMapping("/user")
	@Slf4j
	public class PatientController {
		
		@Autowired
		private PatientService patientServ;
		
		@Autowired
		private SecurityContextLogoutHandler securityContextLogoutHandler;
		
		@GetMapping("/userLogin")
		public String login()
		{
			return "Welcome user";
		}
		
		//API for add 
		@PostMapping("/insertPatient")
		public Patient addPatient(@RequestBody Patient patient)
		{
			log.info("Inside add method of controller");
			return patientServ.addPatient(patient);
		}
		
		//API for find by id
		@GetMapping("/patientId/{id}")
		public Patient getById(@PathVariable("id") Integer id)
		{
			log.info("Inside get by id of controller");
			return patientServ.getById(id);
		}
		
		//API for get the doctors by name
		@GetMapping("/patientName/{name}")
		public List<Patient> getByName(@PathVariable("name") String name)
		{
			log.info("Inside get by name of controller");
			return patientServ.getByName(name);
		}
		
		//API for get all the users 
		@GetMapping("/patientAll")
		public List<Patient> getAll()
		{
			log.info("Inside get all method of controller");
			return patientServ.getAllPatientss();
		}
		
		//API for update a patient
		@PutMapping("/updatePatient/{id}")
		public Patient updatePatient(@PathVariable("id") Integer id,@RequestParam Long phoneNumber)
		{
			log.info("Inside update method of controller");
			return patientServ.updatePatient(id, phoneNumber);
		}
		
		//API for delete a patient
		@DeleteMapping("/deletePatient/{id}")
		public String deletePatient(@PathVariable("id") Integer id)
		{
			log.info("Inside delete method of controller");
			return patientServ.deletePatient(id);
		}
		
		//API for find by email
//		@GetMapping("/patientEmail/{email}")
//		public Patient getByEmail(@PathVariable("email") String email)
//		{
//			log.info("Inside find by email method of controller");
//			return patientServ.findByPatientEmail(email);
//		}
		
		@GetMapping("/userLogout")
	    public ResponseEntity<String> logoutUser(HttpServletRequest request, HttpServletResponse response) {
	        securityContextLogoutHandler.logout(request, response, null);
	        return ResponseEntity.ok("Successfully logged out from user profile");
	    }
		

	}


