package com.example.mongodb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.mongodb.collection.Employee;
import com.example.mongodb.repo.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee saveEmployee(Employee employee) {
		return mongoTemplate.save(employee);
	}

	public List<Employee> getAllEmployee(String name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("employeeName").regex("^S").orOperator(Criteria.where("employeeId").is("1")));

//		return employeeRepository.findEmployeeByEmployeeName(name);
		return mongoTemplate.find(query, Employee.class);
	}

}
