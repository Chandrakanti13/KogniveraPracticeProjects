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

import com.kog.beans.Doctor;
import com.kog.services.DoctorService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/admin")
@Slf4j
public class DoctorController {
	
	@Autowired
	private DoctorService doctServ;
	
	@Autowired
	private SecurityContextLogoutHandler securityContextLogoutHandler;
	
	@GetMapping("/adminLogin")
	public String login()
	{
		return "Welcome admin";
	}
	
	//API for add 
	@PostMapping("/insertDoctor")
	public Doctor addDoctor(@RequestBody Doctor doctor)
	{
		log.info("Inside add method of controller");
		return doctServ.addDoctor(doctor);
	}
	
	//API for find by id
	@GetMapping("/doctorId/{id}")
	public Doctor getById(@PathVariable("id") Integer id)
	{
		log.info("Inside get by id of controller");
		return doctServ.getById(id);
	}
	
	//API for get the doctors by name
	@GetMapping("/doctorName/{name}")
	public List<Doctor> getByName(@PathVariable("name") String name)
	{
		log.info("Inside get by name of controller");
		return doctServ.getByName(name);
	}
	
	//API for get all the users 
	@GetMapping("/doctorAll")
	public List<Doctor> getAll()
	{
		log.info("Inside get all method of controller");
		return doctServ.getAllDoctors();
	}
	
	//API for update a doctor
	@PutMapping("/updateDoctor/{id}")
	public Doctor updateDoctor(@PathVariable("id") Integer id,@RequestParam Long phoneNumber)
	{
		log.info("Inside update method of controller");
		return doctServ.updateDoctor(id, phoneNumber);
	}
	
	//API for delete a doctor
	@DeleteMapping("/deleteDoctor/{id}")
	public String deleteDoctor(@PathVariable("id") Integer id)
	{
		log.info("Inside delete method of controller");
		return doctServ.deleteDoctor(id);
	}
	
	//API for find by email
//	@GetMapping("/doctorEmail/{email}")
//	public Doctor getByEmail(@PathVariable("email") String email)
//	{
//		log.info("Inside find by email method of controller");
//		return doctServ.findByDoctorEmail(email);
//	}
	
	@GetMapping("/adminLogout")
    public ResponseEntity<String> logoutAdmin(HttpServletRequest request, HttpServletResponse response) {
        securityContextLogoutHandler.logout(request, response, null);
        return ResponseEntity.ok("Successfully logged out from admin profile");
    }
	

}
