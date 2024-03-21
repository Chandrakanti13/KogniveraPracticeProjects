package com.kog.Car.controller;

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

import com.kog.Car.beans.Car;
import com.kog.Car.service.CarService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/car")
@Slf4j
public class CarController {
	
	@Autowired
	private CarService carService;
	
	
	
	//For getting All the Cars from the database
	@GetMapping("/allCars")
	public List<Car> getAllCars()
	{
		return carService.getAllCars();
	}
	
	//For getting a Car By regNo
	@GetMapping("/get/{regNo}")
	public Car getById(@PathVariable("regNo") int regNo)
	{
		log.info("get by regNo");
		return carService.getByRegNo(regNo);
	}
	
	//For getting by brand
	@GetMapping("/brand/{brand}")
	public List<Car> getByBrand(@PathVariable("brand") String brand)
	{
		log.info("get by brand");
		return carService.getByBrand(brand);
	}
	
	//Create a Car record
	@PostMapping
	public Car addCar(@RequestBody Car car)
	{
		log.info("save");
		return carService.insertCar(car);
	}
	
	//For updating a Record
	@PutMapping("/update/{regNo}")
	public Car updateCar(@PathVariable("regNo") int regNo,@RequestBody Car car)
	{
		log.info("update");
		return carService.updateCar(regNo, car);
	}
	
	//For deleting a record
	@DeleteMapping("delete/{regNo}")
	public String deleteCar(@PathVariable("regNo") int regNo)
	{
		log.info("delete");
		return carService.deleteCar(regNo);
	}
	{
		
	}
	
	

}
