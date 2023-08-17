package com.autobotix.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ContactUs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer contactUsId;
	@NotNull(message = "Name Is Required")
	private String name;
	@NotNull(message = "Subject Is Required")
	private String subject;
	@NotNull(message = "Email Is Required")
	private String email;
	@NotNull(message = "Phone Number Is Required")
	private Long phone;
	@Lob
	private String message;

}
