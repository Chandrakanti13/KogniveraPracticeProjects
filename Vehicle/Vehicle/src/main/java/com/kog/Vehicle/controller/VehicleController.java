package com.kog.Vehicle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.kog.Vehicle.dto.Vehicle;
import com.kog.Vehicle.service.VehicleService;

@RestController
public class VehicleController {
	
	private VehicleService vehicleService;
	
	@Autowired
	public void setVehicleService(VehicleService vehicleService)
	{
		this.vehicleService=vehicleService;
	}
	
	@PostMapping("/insert")
	public Vehicle addVehicle(@RequestBody Vehicle vc)
	{
		return vehicleService.addVehicle(vc);
	}
	
	@GetMapping("/getAll")
	public List<Vehicle> getAll()
	{
		return vehicleService.getAll();
	}
	
	@GetMapping("/getById")
	public Vehicle getById(@RequestParam Integer id)
	{
		return vehicleService.getById(id);
	}
	
	@PutMapping("/update")
	public Vehicle updateVehicle(@RequestParam Integer id,@RequestParam String color)
	{
		return vehicleService.updateVehicle(id, color);
	}
	
	@DeleteMapping("/delete")
	public String deleteVehicle(@RequestParam Integer id)
	{
		return vehicleService.deleteVehicle(id);
	}
	


}
