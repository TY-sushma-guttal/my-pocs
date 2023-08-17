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
public class UserDto {

	private String name;
	@NotNull(message = "userType required")
	private String userType;
	@NotNull(message = "user contact number required")
	private Long userContactNumber;
	@NotNull(message = "user email id required")
	private String email;
	@NotNull(message = "username required")
	private String userName;
	private Boolean status;
	@NotNull(message = "password required")
	private String password;
}
