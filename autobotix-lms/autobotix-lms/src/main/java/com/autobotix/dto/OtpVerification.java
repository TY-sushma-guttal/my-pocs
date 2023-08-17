package com.autobotix.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OtpVerification {
	
	@NotNull(message = "email is required")
	private String email;
	@NotNull(message = "enter otp")
	private Integer otp;

}
