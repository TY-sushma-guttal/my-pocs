package com.tyss.springbatch.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class User {

	@Id
	private Integer id;
	
	private String name;
	
	private String dept;
	
	private Integer salary;
}
