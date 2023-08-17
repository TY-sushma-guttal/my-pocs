package com.autobotix.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditTeacherDto {

	private Integer id;
	private String name;
	private String email;
	private Integer age;
	private Long contactNum;
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dateOfBirth;
	private String gender;
	private List<String> grades;
	private String password;
}
