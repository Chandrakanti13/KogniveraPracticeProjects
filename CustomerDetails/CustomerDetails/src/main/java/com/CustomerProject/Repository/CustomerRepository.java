package com.CustomerProject.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.CustomerProject.Beans.Customer;
@Repository
public interface CustomerRepository extends MongoRepository<Customer, Integer>{
	
	@Query("{'customerName':?0}")
	List<Customer> findByName(String customerName);
	@Query("{'customerEmail':?0}")
	List<Customer> findByEmail(String customerEmail);
	@Query("{'customerGender':?0}")
	List<Customer> findByGender(String customerGender);
	@Query("{'customerAddress':?0}")
	List<Customer> findByAddress(String customerAddress);
	

}
