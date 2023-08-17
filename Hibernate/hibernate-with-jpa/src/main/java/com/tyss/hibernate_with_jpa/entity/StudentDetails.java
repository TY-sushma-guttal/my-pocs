package com.tyss.hibernate_with_jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "tbl_student")
public class StudentDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = true, updatable = true, nullable = false, name = "school_id")
	private Long studentId;
	
	@Length(min = 0, max = 100)
	private String studentName;
	

}
