package com.autobotix.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	@NotNull(message = "name required")
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
