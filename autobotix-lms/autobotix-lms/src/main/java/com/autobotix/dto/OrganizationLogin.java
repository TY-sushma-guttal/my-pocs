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
public class OrganizationLogin {
	
	@NotNull(message = "enter organization id or email id")
	private String orgId;
	@NotNull(message = "password cant be null")
	private String password;

}
