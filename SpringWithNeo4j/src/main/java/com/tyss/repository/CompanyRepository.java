package com.tyss.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.tyss.pojo.Company;

public interface CompanyRepository extends Neo4jRepository<Company, Long> {

	List<Company> findByCompanyName(String name);
}
