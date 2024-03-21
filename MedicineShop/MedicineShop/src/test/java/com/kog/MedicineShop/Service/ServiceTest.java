package com.kog.MedicineShop.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import com.kog.MedicineShop.Model.Medicine;
import com.kog.MedicineShop.Repository.MedicineRepository;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {
	@InjectMocks
	private MedicineService medService;
	
	@Mock
	private MedicineRepository medRepository;
	
	@BeforeEach
	public void init()
	{
		MockitoAnnotations.openMocks(this);
	}
	
	
	
	
	@Test
	void testAddMedicine()
	{
		Medicine med=new Medicine(3,"j","i","9","0",1l);
		when(medRepository.save(med)).thenReturn(med);
		Medicine savedMedicine=medService.addMedicine(med);
		verify(medRepository,times(1)).save(med);
		assertEquals(med, savedMedicine);
		
	}
	
	@Test
	void testDeleteMedicine()
	{
		Medicine med=new Medicine(4,"j","i","9","0",1l);
		when(medRepository.findById(med.getMedicineRegNo())).thenReturn(Optional.of(med));
	    String result=medService.deleteMedicine(med.getMedicineRegNo());
	    verify(medRepository,times(1)).delete(med);
	    assertEquals("Medicine with regNo "+med.getMedicineRegNo()+" got deleted", result);
	}
	
	@Test
	void testGetByMedRegNo()
	{
		Optional<Medicine> existMed=Optional.of((new Medicine(1,"p","o","0","9",1l)));
	    when(medRepository.findById(1)).thenReturn(existMed);
	    Optional<Medicine> actualMed=Optional.of(medService.getAMedicine(1));
	    assertEquals(existMed, actualMed);
	}
	
	@Test
	void testGetAllMedicines()
	{
		List<Medicine> medicines=new ArrayList<Medicine>();
		Medicine med1=new Medicine(1,"k","l","k","j",9l);
		Medicine med2=new Medicine(2,"k","l","k","j",9l);
		
		medicines.add(med1);
		medicines.add(med2);
		
		
		when(medRepository.findAll()).thenReturn(medicines);
		List<Medicine> medList=medService.getAllMedicines();
		
		assertEquals(2, medList.size());
		verify(medRepository,times(1)).findAll();	
	}
	
	@Test
	void testUpdateMedicine()
	{
		Medicine med=new Medicine(1,"p","o","1","3",1l);
		when(medRepository.findById(med.getMedicineRegNo())).thenReturn(Optional.of(med));
		when(medRepository.save(Mockito.any(Medicine.class))).thenReturn(med);
		Medicine med1=medService.updateMedicine(1, med);
		assertEquals(med, med1);
	}
	
}
