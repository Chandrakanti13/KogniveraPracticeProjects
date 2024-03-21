package com.kog.MedicineShop.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kog.MedicineShop.Model.Medicine;
import com.kog.MedicineShop.Repository.MedicineRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MedicineService {
	@Autowired
	private MedicineRepository repository;
	
	//Add medicine to database
	public Medicine addMedicine(Medicine medicine)
	
	{
		log.info("Inside add method");
		return repository.save(medicine);
	}
	
	//Get all the medicines present in database
	public List<Medicine> getAllMedicines()
	{
		log.info("Inside getall method");
		return repository.findAll();
	}
	
	//Get a specific medicine by medicineRegNo
	public Medicine getAMedicine(int medicineRegNo)
	
	{
		log.info("Inside get by id method");
		Optional<Medicine> medicine=repository.findById(medicineRegNo);
		if(medicine.isPresent())
		{
			return medicine.get();
		}
		else
		{
			return null;
		}
	}
	
	//Update a medicine based on medicineRegNo
	public Medicine updateMedicine(int medicineRegNo,Medicine medicine)
	{
		log.info("Inside update method");
		Optional<Medicine> existMedicine=repository.findById(medicineRegNo);
		if(existMedicine.isPresent())
		{
			Medicine updateMedicine=existMedicine.get();
			updateMedicine.setType(medicine.getType());
			updateMedicine.setMedicineName(medicine.getMedicineName());
			updateMedicine.setExpiryDate(medicine.getExpiryDate());
			updateMedicine.setManufactureDate(medicine.getManufactureDate());
			updateMedicine.setStock(medicine.getStock());
			Medicine savedMedicine=repository.save(updateMedicine);
			return savedMedicine;
		}
		else
		{
			return null;
		}
	}
	
	//Delete a medicine by medicine
	public String deleteMedicine(int medicineRegNo)
	{
		log.info("Inside delete method");
		Medicine medicine=getAMedicine(medicineRegNo);
		if(medicine!=null)
		{
			repository.delete(medicine);
			return "Medicine with regNo "+ medicineRegNo +" got deleted";
		}
		else
		{
			return "Medicine with regNo "+ medicineRegNo +" is not found";
			
		}
	}
	
	//sorting
	public List<Medicine> sortByField(String field)
	{
		return repository.findAll(Sort.by(Sort.Direction.DESC,field));
		//return fac.findAll(Sort.by(Sort.Direction.ASC,field));
		//return fac.findAll(Sort.by(field));
		
	}
	//pagination
	public Page<Medicine> getPage(int offset,int size)
	{
		return repository.findAll(PageRequest.of(offset, size));
	}
	//pagination & sorting
	public Page<Medicine> getPageSort(int offset,int size,String field)
	{
		return repository.findAll(PageRequest.of(offset, size).withSort(Sort.by(Sort.Direction.DESC,field)));
	}

}
