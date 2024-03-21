package com.kog.LibraryManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kog.LibraryManagementSystem.beans.User;
import com.kog.LibraryManagementSystem.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/getAllUsers")
	public List<User> getAllUser()
	{
		return userService.getAlUsers();
	}
	
	@PostMapping
	public User addUser(@RequestBody User u)
	{
		return userService.addUser(u);
	}
	
	@GetMapping("/{id}")
	public User getSingleUser(@PathVariable("id") Integer id){
		return userService.getUser(id);
	}
	@PutMapping("/{id}/{name}")
	public User updateUser(@PathVariable("id") Integer id, @PathVariable("name") String name)
	{
		return userService.updateUser(id, name);
	}
	
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable("id") Integer id)
	{
		return userService.deleteUser(id);
	}
	

}
