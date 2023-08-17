package com.autobotix.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentProfileResponse {

	private String studentId;
	private String firstName;
	private String lastName;
	private String email;
	private Long contactNumber;
	private LocalDate dateOfBirth;
	private String school;
	private String grade;
	private String fathersName;
	private String mothersName;

}
