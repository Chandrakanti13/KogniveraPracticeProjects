package com.TestProject.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.TestProject.Model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, Integer>{

}
