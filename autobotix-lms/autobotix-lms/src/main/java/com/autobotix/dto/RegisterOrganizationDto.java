package com.autobotix.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterOrganizationDto {

	@NotNull(message = "Organization Id Required")
	@Length(max = 16)
	private String organizationId;
	@NotNull(message = "Organization Name Required")
	private String organizationName;
	@NotNull(message = "Organization Address Required")
	@Length(max = 200)
	private String address;
	@NotNull(message = "Organization Contact Number Required")
	@Length(max = 10)
	@Column(unique = true)
	private String contactNumber;
	@NotNull(message = "School Board Required")
	private String schoolsBoard;
	@NotNull(message = "Organization Email Id Required")
	private String email;
	@NotNull(message = "Organization Principal Name Required")
	private String principalName;
	@NotNull(message = "Organization Principal Contact Number Required")
	@Length(max = 10)
	@Column(unique = true)
	private String principalContactNo;
	@NotNull(message = "Organization Principal Email Id Required")
	private String principalEmailId;
	private String branchCode;
	@NotNull(message = "Password Required")
	private String password;

}
