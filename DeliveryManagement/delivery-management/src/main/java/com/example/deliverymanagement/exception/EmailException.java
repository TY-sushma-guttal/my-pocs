package com.example.deliverymanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EmailException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String message;

}
