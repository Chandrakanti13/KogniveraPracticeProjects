package com.kog.Laptop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kog.Laptop.beans.Laptop;
import com.kog.Laptop.repository.LaptopRepository;

@Service
public class LaptopService {
	
	@Autowired
	private LaptopRepository laptopRepository;
	
	public Laptop addLaptop(Laptop laptop)
	{
		return laptopRepository.save(laptop);
	}
	
	public List<Laptop> getAllLaptops()
	{
		return laptopRepository.findAll();
	}
	
	public Laptop getById(Integer id)
	{
		Optional<Laptop> laptop=laptopRepository.findById(id);
		if(laptop.isPresent())
		{
			return laptop.get();
		}
		else
		{
			return null;
		}
	}
	
	public Laptop updateLaptop(Integer id,Laptop laptop)
	{
		Laptop existLaptop=getById(id);
		if(existLaptop!=null)
		{
			existLaptop.setLaptopBrand(laptop.getLaptopBrand());
			existLaptop.setLaptopModel(laptop.getLaptopModel());
			existLaptop.setLaptopPrice(laptop.getLaptopPrice());
			laptopRepository.save(existLaptop);
			return existLaptop;
		}
		else
		{
			return null;
		}
	}
	
	public String deleteLaptop(Integer id)
	{
		Laptop laptop=getById(id);
		if(laptop!=null)
		{
			laptopRepository.delete(laptop);
			return "Laptop with id "+ id+" got deleted";
		}
		else
		{
			return "Laptop with id "+ id +" not available";
		}
	}

}
