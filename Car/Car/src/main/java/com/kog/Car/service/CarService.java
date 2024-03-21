package com.kog.Car.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kog.Car.beans.Car;
import com.kog.Car.repository.CarRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	//Get All The cars present in database
	public List<Car> getAllCars()
	{
		log.info("Inside the getAll method");
		return carRepository.findAll();
	}
	
	//Insert a Car into the database
	public Car insertCar(Car car)
	{
		log.info("Inside the create method");
		return carRepository.save(car);
	}
	
	//Get a Car by regNo
	public Car getByRegNo(int regNo)
	{
		log.info("Inside get method by regNo");
		Optional<Car> car=carRepository.findById(regNo);
		if(car.isPresent())
		{
			return car.get();
		}
		else
		{
			return null;
		}
	}
	
	
	//Get by brand
	public List<Car> getByBrand(String brand)
	{
		log.info("Inside get method by brand");
		return carRepository.getByBrand(brand);
	}
	//Update a Car based on regNo
	public Car updateCar(int regNo,Car updateCar)
	{
		log.info("Inside update method");
		Car existingCar=getByRegNo(regNo);
		if(existingCar!=null)
		{
			existingCar.setBrand(updateCar.getBrand());
			existingCar.setModel(updateCar.getModel());
			existingCar.setPrice(updateCar.getPrice());
			carRepository.save(existingCar);
			return existingCar;
		}
		else
		{
			return null;
		}
	}
	
	//Delete a Car by regNo
	public String deleteCar(int regNo)
	{
		log.info("Inside delete method");
		Car existCar=getByRegNo(regNo);
		if(existCar!=null)
		{
			carRepository.delete(existCar);
			return "Car with regNo "+ regNo +" got deleted";
		}
		else
		{
			return "Car with regNo "+regNo +" is not found";
		}
	}
	

}
