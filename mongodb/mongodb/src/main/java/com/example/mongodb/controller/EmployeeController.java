package com.example.mongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mongodb.collection.Employee;
import com.example.mongodb.service.EmployeeService;
import com.example.mongodb.util.ResponseMaster;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<ResponseMaster> saveEmployee(@RequestBody Employee employee) {
		Employee response = employeeService.saveEmployee(employee);
		if (response != null)
			return new ResponseEntity<ResponseMaster>(ResponseMaster.builder().data(response)
					.message("Employee details are saved successfully.").error(false).build(), HttpStatus.OK);
		else
			return new ResponseEntity<ResponseMaster>(ResponseMaster.builder().data(response)
					.message("Unable to save employee details.").error(true).build(), HttpStatus.NOT_FOUND);

	}

	@GetMapping("/{name}")
	public ResponseEntity<ResponseMaster> getAllEmployee(@PathVariable String name) {
		List<Employee> response = employeeService.getAllEmployee(name);
		if (!response.isEmpty())
			return new ResponseEntity<ResponseMaster>(ResponseMaster.builder().data(response)
					.message("Employee details are fetched successfully.").error(false).build(), HttpStatus.OK);
		else
			return new ResponseEntity<ResponseMaster>(
					ResponseMaster.builder().data(response).message("No Employee Data Found!").error(true).build(),
					HttpStatus.NOT_FOUND);

	}

}
