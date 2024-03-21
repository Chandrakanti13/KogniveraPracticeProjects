package com.kog.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kog.dto.UserDto;
import com.kog.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/registration")
	public String getRegistrationPage(@ModelAttribute("user") UserDto userDto)
	{
		return "register";
	}
	
	@PostMapping("/registration")
	public String saveUser(@ModelAttribute("user") UserDto userDto,Model model)
	{
		userService.save(userDto);
		model.addAttribute("message","Registered Successfully.");
		return "register";
	}
	
	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
	
	@GetMapping("/userPage")
	public String userPage(Model model,Principal principal)
	{
		UserDetails userDetails=userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user",userDetails);
		return "user";

	}
	
	@GetMapping("/adminPage")
	public String adminPage(Model model,Principal principal)
	{
		UserDetails userDetails=userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);
		return "admin";
	}

}
