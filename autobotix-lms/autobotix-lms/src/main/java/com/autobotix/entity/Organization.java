package com.autobotix.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Organization implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
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
	@Column(unique = true)
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
	
	private Boolean isVerified = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "adminId")
	private Admin admin;
}
