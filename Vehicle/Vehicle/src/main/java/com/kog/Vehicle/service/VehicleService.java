package com.kog.Vehicle.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kog.Vehicle.dto.Vehicle;
import com.kog.Vehicle.repository.VehicleRepository;

@Service
public class VehicleService {
	
	private VehicleRepository vehicleRepository;
	
	@Autowired
	public void setVehivleRepository(VehicleRepository vehicleRepository)
	{
		this.vehicleRepository=vehicleRepository;
	}
	
	public Vehicle addVehicle(Vehicle vc)
	{
		return vehicleRepository.save(vc);
	}
	
	public List<Vehicle> getAll()
	{
		return vehicleRepository.findAll();
	}
	
	public Vehicle getById(Integer id)
	{
		Optional<Vehicle> opt=vehicleRepository.findById(id);
		if(opt.isPresent())
		{
			return opt.get();
		}
		else
		{
			return null;
		}
		
	}
	
	public Vehicle updateVehicle(Integer id,String color)
	{
		Vehicle vc=getById(id);
		if(vc!=null)
		{
			vc.setColor(color);
			vehicleRepository.save(vc);
			return vc;
		}
		else
		{
			return null;
		}
		
	}
	
	public String deleteVehicle(Integer id)
	{
		Vehicle vc=getById(id);
		if(vc!=null)
		{
			vehicleRepository.delete(vc);
			return "Vehicle with regNo "+ id +" got deleted";
		}
		else
		{
			return "Vehicle with regNo "+id +" is not found";
		}
	}

}
