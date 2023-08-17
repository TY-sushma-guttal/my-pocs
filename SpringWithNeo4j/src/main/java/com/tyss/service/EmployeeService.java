package com.tyss.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.pojo.Company;
import com.tyss.repository.CompanyRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	public List<Company> saveCompany(List<Company> companyList) {
		List<Company> saveAll = companyRepository.saveAll(companyList);
		return saveAll;
	}

}
