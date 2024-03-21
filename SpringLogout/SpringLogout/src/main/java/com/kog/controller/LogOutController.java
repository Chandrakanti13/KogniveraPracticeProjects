package com.kog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/out")
public class LogOutController {
	
	@Autowired
	private SecurityContextLogoutHandler securityContextLogoutHandler;
	
	@GetMapping("/all")
	public String allProfile()
	{
		return "Welcome to all to the profile";
	}
	
	@GetMapping("/userProfile")
	public String userProfile()
	{
		return "Welcome user";
	}
	
	@GetMapping("/adminProfile")
	public String adminProfile()
	{
		return "Welcome admin";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response)
	{
		securityContextLogoutHandler.logout(request, response, null);
		return "Successfully logged out";
	}
	

}
