package com.kog.Laptop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kog.Laptop.beans.Laptop;

@Repository
public interface LaptopRepository extends MongoRepository<Laptop, Integer>{

}
