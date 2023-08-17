package com.autobotix.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentVerificationDto {
	
	@NotNull(message = "id required")
	private String id;
	@NotNull(message = "otp required")
	private Integer otp;

}
