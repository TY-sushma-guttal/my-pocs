package com.autobotix.exceptions;

@SuppressWarnings("serial")
public class CourseNotFoundException extends RuntimeException {

	public CourseNotFoundException(String message) {
		super(message);
	}

}
