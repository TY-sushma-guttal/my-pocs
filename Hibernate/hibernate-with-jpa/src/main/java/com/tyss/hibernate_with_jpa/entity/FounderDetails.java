package com.tyss.hibernate_with_jpa.entity;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class FounderDetails {
	
	private String name;
	
	private String emailId;
	
	private Long phoneNo;

}
