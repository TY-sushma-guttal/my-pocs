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
public class StudentLogin {
	
	@NotNull(message = "Required Email Id Or Student Id")
	private String userId;
	@NotNull(message = "Password is Missing")
	private String password;
}
