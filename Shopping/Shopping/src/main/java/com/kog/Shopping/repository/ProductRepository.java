package com.kog.Shopping.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.kog.Shopping.beans.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, Long>{
	@Query("{'name':?0}")
	List<Product> getByName(String name);

}
