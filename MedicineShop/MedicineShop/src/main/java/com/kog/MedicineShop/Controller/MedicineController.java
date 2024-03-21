package com.kog.MedicineShop.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.kog.MedicineShop.Model.Medicine;
import com.kog.MedicineShop.Service.MedicineService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@Slf4j
@RequestMapping("/api")
public class MedicineController {
	
	@Autowired 
	private MedicineService service;
	  
//	@GetMapping("/welcome")
//	public String Welcome()
//	{
//		return "Welcome this end point is not secure";
//	}
//	
//	@GetMapping("/user/userProfile")
//	@PreAuthorize("hasAuthority('ROLE_USER')")
//	public String userProfile()
//	{
//		return "Welcome to user profile";
//	}
//	
//	@GetMapping("/admin/adminProfile")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
//	public String adminProfile()
//	{
//		return "Weclome to admin profile";
//	}
	
	//API FOR CREATE
	
	@PostMapping("/insert")
	public Medicine addMedicine(@RequestBody Medicine medicine)
	{
		log.info("insert");
		return service.addMedicine(medicine);
	}
	
	//API FOR GET ALL MEDICINES
	
	@GetMapping("/getAll")
	public List<Medicine> getAllMedicines()
	{
		log.info("getAll");
		return service.getAllMedicines();
	}
	
	//API FOR GETTING A SPECIFIC MEDICINE
	
	@GetMapping("/get/{regNo}")
	public Medicine getMedicine(@PathVariable("regNo") int medicineregNo)
	{
		log.info("get");
		return service.getAMedicine(medicineregNo);
	}
	
	//API FOR UPDATE 
	
	@PutMapping("/update/{regNo}")
	public Medicine updateMedicine(@PathVariable("regNo") int medicineRegNo,@RequestBody Medicine medicine)
	{
		log.info("update");
		return service.updateMedicine(medicineRegNo, medicine);
	}
	
	//API FOR DELETE
	
	@DeleteMapping("/delete/{regNo}")
	public String deleteMedicine(@PathVariable("regNo") int medicineRegNo)
	{
		log.info("delete");
		return service.deleteMedicine(medicineRegNo);
	}
	
	//API FOR SORTING
	@GetMapping("/sort/{field}")
	public List<Medicine> sortByField(@PathVariable("field") String field)
	{
		return service.sortByField(field);
	}
	
	//API FOR PAGING
	@GetMapping("/paging/{offset}/{size}")
	public Page<Medicine> getPage(@PathVariable("offset") int offset,@PathVariable("size") int size)
	{
		return service.getPage(offset, size);
	}
	
	//API FOR BOTH SORTING AND PAGING
	@GetMapping("/both/{offset}/{size}/{field}")
	public Page<Medicine> getPageSort(@PathVariable("offset") int offest,@PathVariable("size") int size,@PathVariable("field") String field)

	{
		return service.getPageSort(offest, size, field);
	}
	
	
	
	

}
