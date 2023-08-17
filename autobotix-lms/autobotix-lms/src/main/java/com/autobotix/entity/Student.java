package com.autobotix.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "student_id")
	@GenericGenerator(name = "student_id", strategy = "com.autobotix.util.StudentIdGenerator")
	@Length(max = 16)
	private String id;
	@NotNull(message = "student first name is required")
	private String firstName;
	@NotNull(message = "student last name is required")
	private String lastName;
	@NotNull(message = "student email is required")
	@Column(unique = true)
	private String email;
	@NotNull(message = "student contact number is required")
	@Length(max = 10)
	@Column(unique = true)
	private String contactNumber;
	@NotNull(message = "date required")
	@JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
	private LocalDate dateOfBirth;
	@NotNull(message = "student school is required")
	private String school;
	@NotNull(message = "student grade is required")
	private String grade;
	@NotNull(message = "student's father name is required")
	private String fathersName;
	private String fathersProfession;
	private String fatherEmail;
	@Length(max = 10)
	@Column(unique = true)
	private String fathersNumber;
	@NotNull(message = "student's mothers name is required")
	private String mothersName;
	@NotNull(message = "password is required")
	private String password;
	private Boolean isVerified = false;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "adminId")
	private Admin admin;
	

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CourseOnDashBoard> courses;

}
