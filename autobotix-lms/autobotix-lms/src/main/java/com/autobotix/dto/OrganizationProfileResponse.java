package com.autobotix.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationProfileResponse {

	private String organizationId;
	private String organizationName;
	private String organizationContactNo;
	private String schoolsBoard;
	private String organizationEmailId;
	private String principalName;
	private String principalContactNo;
	private String branchCode;

}
