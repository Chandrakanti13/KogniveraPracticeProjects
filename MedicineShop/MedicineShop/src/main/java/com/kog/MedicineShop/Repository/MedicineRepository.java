package com.kog.MedicineShop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kog.MedicineShop.Model.Medicine;
@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Integer>{

}
