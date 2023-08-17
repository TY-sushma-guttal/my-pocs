package com.tyss.hibernate_with_jpa.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "tbl_school")
public class SchoolDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = true, updatable = true, nullable = false, name = "school_id")
	private Long schoolId;
	
	@Length(min = 0, max = 100)
	private String schoolName;
	
	@Size(min = 0, max = 100)
	private String universityName; 
	
	@Embedded
	private FounderDetails founderDetails;
	
	@OneToMany
	private List<StudentDetails> studentList ;

}
