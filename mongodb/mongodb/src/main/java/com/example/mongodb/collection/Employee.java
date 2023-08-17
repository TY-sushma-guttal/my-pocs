package com.example.mongodb.collection;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
	
	private Integer employeeId;
	
	private String employeeName;

}
