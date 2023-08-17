package com.tyss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.pojo.Company;
import com.tyss.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/save/company")
	public ResponseEntity<List<Company>> saveCompanyList(@RequestBody List<Company> companyList) {
		List<Company> saveCompany = employeeService.saveCompany(companyList);
		return new ResponseEntity<>(saveCompany, HttpStatus.CREATED);
	}
	
	@GetMapping("/get")
	public ResponseEntity<String> test() {
		return new ResponseEntity<String>("H", HttpStatus.OK);
		
	}
	
	
}
