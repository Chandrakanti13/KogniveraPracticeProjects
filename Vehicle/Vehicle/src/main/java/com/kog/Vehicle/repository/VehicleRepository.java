package com.kog.Vehicle.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kog.Vehicle.dto.Vehicle;
@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, Integer>{

}
