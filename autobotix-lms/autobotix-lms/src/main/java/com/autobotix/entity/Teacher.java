package com.autobotix.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.autobotix.util.ListStringConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private Integer age;
	private Long contactNum;
	@JsonFormat(pattern = "dd-MM-yyyy", shape = Shape.STRING)
	private LocalDate dateOfBirth;
	private String gender;
	@Convert(converter = ListStringConverter.class)
	private List<String> grades;
	private String password;
	private Boolean isActive;
	private Boolean isVerified;

}
