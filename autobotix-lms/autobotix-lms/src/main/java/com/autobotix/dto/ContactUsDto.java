package com.autobotix.dto;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactUsDto {

	@NotNull(message = "Name Is Required")
	private String name;
	@NotNull(message = "Subject Is Required")
	private String subject;
	@NotNull(message = "Email Is Required")
	private String email;
	@NotNull(message = "Phone Number Is Required")
	private Long phone;
	@Lob
	private String message;
}
