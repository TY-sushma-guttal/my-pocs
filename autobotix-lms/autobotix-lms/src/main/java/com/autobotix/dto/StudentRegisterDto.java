package com.autobotix.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRegisterDto {

	@NotNull(message = "firstname required")
	private String firstName;
	@NotNull(message = "lastname required")
	private String lastName;
	@NotNull(message = "date required")
	@JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
	private LocalDate dateOfBirth;
	@NotNull(message = "email required")
	private String email;
	@NotNull(message = "contact no reequired")
	@Length(max = 10)
	@Column(unique = true)
	private String contactNumber;
	@NotNull(message = "school name required")
	private String school;
	@NotNull(message = "grade required")
	private String grade;
	@NotNull(message = "students father name required")
	private String fathersName;
	private String fathersProfession;
	private String fatherEmail;
	@Length(max = 10)
	@Column(unique = true)
	private String fathersNumber;
	@NotNull(message = "students mother name required")
	private String mothersName;
	@NotNull(message = "password required")
	private String password;
}
