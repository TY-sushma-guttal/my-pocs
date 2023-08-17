package com.example.deliverymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserDetailsDTO {
	
	private Long userId;

	private String userName;

	private Long phoneNumber;

	private String emailId;

}
