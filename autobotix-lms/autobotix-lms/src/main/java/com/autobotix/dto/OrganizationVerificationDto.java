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
public class OrganizationVerificationDto {
	
	@NotNull(message = "Need Contact Number")
	private String contactNumber;
	@NotNull(message = "Enter OTP For Verification")
	private Integer otp;
}
