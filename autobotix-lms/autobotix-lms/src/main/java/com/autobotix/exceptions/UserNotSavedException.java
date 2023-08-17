package com.autobotix.exceptions;

public class UserNotSavedException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotSavedException(String message) {
		super(message);
	}

}
