package com.kog.Laptop.controller;

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

import com.kog.Laptop.beans.Laptop;
import com.kog.Laptop.service.LaptopService;

@RestController
@RequestMapping("api/laptop")
public class LaptopController {
	@Autowired
	private LaptopService laptopService;
	
	@PostMapping
	public Laptop addLaptop(@RequestBody Laptop laptop)
	{
		return laptopService.addLaptop(laptop);
	}
	
	@GetMapping("/getAll")
	public List<Laptop> getAllLaptops()
	{
		return laptopService.getAllLaptops();
	}
	
	@GetMapping("/id/{id}")
	public Laptop getById(@PathVariable("id") Integer id)
	{
		return laptopService.getById(id);
	}

	@PutMapping("/update/{id}")
	public Laptop updateLaptop(@PathVariable("id") Integer id,@RequestBody Laptop laptop)
	{
		return laptopService.updateLaptop(id, laptop);
	}
	
	@DeleteMapping("delete/{id}")
	public String deleteLaptop(@PathVariable("id") Integer id)
	{
		return laptopService.deleteLaptop(id);
	}
}
