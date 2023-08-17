package com.example.mongodb.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.mongodb.collection.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, Integer> {

	@Query("{'employeeName' : ?0}")
	List<Employee> findEmployeeByEmployeeName(String name);

}
