package com.kog.Car.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kog.Car.beans.Car;

@Repository 
public interface CarRepository extends JpaRepository<Car, Integer>{
  
	//Get By brand
	@Query(value="select * from tables where brand=?1", nativeQuery = true)
	public List<Car> getByBrand(String brand);

}
