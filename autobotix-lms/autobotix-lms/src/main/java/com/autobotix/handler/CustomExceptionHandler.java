package com.autobotix.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.autobotix.exceptions.AutoBotixException;
import com.autobotix.exceptions.CategoryEditException;
import com.autobotix.exceptions.CategoryNotFoundException;
import com.autobotix.exceptions.ContactException;
import com.autobotix.exceptions.ContactNumberPresentException;
import com.autobotix.exceptions.CourseDeleteException;
import com.autobotix.exceptions.CourseEditException;
import com.autobotix.exceptions.CourseException;
import com.autobotix.exceptions.CourseNotFoundException;
import com.autobotix.exceptions.EmailException;
import com.autobotix.exceptions.EmailPresentException;
import com.autobotix.exceptions.EmptyFileException;
import com.autobotix.exceptions.FeedException;
import com.autobotix.exceptions.FeedNotFoundException;
import com.autobotix.exceptions.FireBaseException;
import com.autobotix.exceptions.IncorrectPasswordException;
import com.autobotix.exceptions.NoDataFoundException;
import com.autobotix.exceptions.OrganizationEmailException;
import com.autobotix.exceptions.OrganizationNotFoundException;
import com.autobotix.exceptions.OtpException;
import com.autobotix.exceptions.SaveUnsuccessfullException;
import com.autobotix.exceptions.StorageException;
import com.autobotix.exceptions.StudentEmailException;
import com.autobotix.exceptions.StudentException;
import com.autobotix.exceptions.StudentNotFoundException;
import com.autobotix.exceptions.TeacherException;
import com.autobotix.exceptions.UserNotFoundException;
import com.autobotix.exceptions.UserNotSavedException;
import com.autobotix.exceptions.UserStatusException;
import com.autobotix.response.AppResponse;

@RestControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(value = AutoBotixException.class)
	public ResponseEntity<AppResponse> autoBotixException(AutoBotixException exception) {
		return new ResponseEntity<>(new AppResponse(true, exception.getMessage(), "Application Error"),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<AppResponse> userNotFoundExceptionHandler(UserNotFoundException exception) {
		return new ResponseEntity<>(new AppResponse(true, exception.getMessage(), "user info not present"),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = CategoryEditException.class)
	public ResponseEntity<AppResponse> categoryEditException(CategoryEditException exception) {
		return new ResponseEntity<>(new AppResponse(true, exception.getMessage(), "category couldn't be modified"),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = CategoryNotFoundException.class)
	public ResponseEntity<AppResponse> categoryNotFoundException(CategoryNotFoundException exception) {
		return new ResponseEntity<>(new AppResponse(true, exception.getMessage(), "category info not found"),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = CourseDeleteException.class)
	public ResponseEntity<AppResponse> courseDeleteException(CourseDeleteException exception) {
		return new ResponseEntity<>(new AppResponse(true, exception.getMessage(), "course couldn't be deleted"),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = CourseEditException.class)
	public ResponseEntity<AppResponse> courseEditException(CourseEditException exception) {
		return new ResponseEntity<>(new AppResponse(true, exception.getMessage(), "course couldn't be modified"),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = CourseNotFoundException.class)
	public ResponseEntity<AppResponse> courseNotFoundException(CourseNotFoundException exception) {
		return new ResponseEntity<>(new AppResponse(true, exception.getMessage(), "course info not found"),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = NoDataFoundException.class)
	public ResponseEntity<AppResponse> noDataFoundException(NoDataFoundException exception) {
		return new ResponseEntity<>(new AppResponse(true, exception.getMessage(), "no data found"),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = IncorrectPasswordException.class)
	public ResponseEntity<AppResponse> incorrectPasswordException(IncorrectPasswordException exception) {
		return new ResponseEntity<>(new AppResponse(true, exception.getMessage(), "incorrect password"),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = SaveUnsuccessfullException.class)
	public ResponseEntity<AppResponse> saveUnsuccessfullException(SaveUnsuccessfullException exception) {
		return new ResponseEntity<>(new AppResponse(true, exception.getMessage(), "couldn't save the data"),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = StudentEmailException.class)
	public ResponseEntity<AppResponse> studentEmailException(StudentEmailException exception) {
		return new ResponseEntity<>(new AppResponse(true, exception.getMessage(), "email not found"),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = StudentException.class)
	public ResponseEntity<AppResponse> studentException(StudentException exception) {
		return new ResponseEntity<>(new AppResponse(true, exception.getMessage(), "some error occured"),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = StudentNotFoundException.class)
	public ResponseEntity<AppResponse> studentNotFoundException(StudentNotFoundException exception) {
		return new ResponseEntity<>(new AppResponse(true, exception.getMessage(), "student info not present"),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = UserNotSavedException.class)
	public ResponseEntity<AppResponse> userNotSavedException(UserNotSavedException exception) {
		return new ResponseEntity<>(new AppResponse(true, exception.getMessage(), "couldn't save the user data"),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = UserStatusException.class)
	public ResponseEntity<AppResponse> userStatusException(UserStatusException exception) {
		return new ResponseEntity<>(new AppResponse(true, exception.getMessage(), "status not updated"),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = OrganizationEmailException.class)
	public ResponseEntity<AppResponse> organizationEmailException(OrganizationEmailException exception) {
		return new ResponseEntity<>(new AppResponse(true, exception.getMessage(), "email not found"),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = OrganizationNotFoundException.class)
	public ResponseEntity<AppResponse> organizationNotFoundException(OrganizationNotFoundException exception) {
		return new ResponseEntity<>(new AppResponse(true, exception.getMessage(), "organization info not present"),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = EmailPresentException.class)
	public ResponseEntity<AppResponse> emailPresentException(EmailPresentException exception) {
		return new ResponseEntity<>(new AppResponse(true, exception.getMessage(), "email already exists"),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = ContactNumberPresentException.class)
	public ResponseEntity<AppResponse> contactNumberPresentException(ContactNumberPresentException exception) {
		return new ResponseEntity<>(new AppResponse(true, exception.getMessage(), "contact number already exists"),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = OtpException.class)
	public ResponseEntity<AppResponse> otpException(OtpException exception) {
		return new ResponseEntity<>(new AppResponse(true, exception.getMessage(), "OTP error"), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = CourseException.class)
	public ResponseEntity<AppResponse> courseException(CourseException exception) {
		return new ResponseEntity<>(new AppResponse(true, exception.getMessage(), "Error With Course"),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = ContactException.class)
	public ResponseEntity<AppResponse> contactException(ContactException exception) {
		return new ResponseEntity<>(new AppResponse(true, exception.getMessage(), "Error With Contact Information"),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = EmailException.class)
	public ResponseEntity<AppResponse> emailException(EmailException exception) {
		return new ResponseEntity<>(new AppResponse(true, exception.getMessage(), "Error With Email"),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = FeedException.class)
	public ResponseEntity<AppResponse> feedException(FeedException exception) {
		return new ResponseEntity<>(new AppResponse(true, exception.getMessage(), "Something Wrong In Feed"),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = FeedNotFoundException.class)
	public ResponseEntity<AppResponse> feedNotFoundException(FeedNotFoundException exception) {
		return new ResponseEntity<>(new AppResponse(true, exception.getMessage(), "Error With Feed"),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = StorageException.class)
	public ResponseEntity<AppResponse> storageException(StorageException exception) {
		return new ResponseEntity<>(new AppResponse(true, exception.getMessage(), "Error With Cloud Storage"),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = FireBaseException.class)
	public ResponseEntity<AppResponse> fireBaseException(FireBaseException exception) {
		return new ResponseEntity<>(new AppResponse(true, exception.getMessage(), "Error With FireBase"),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = EmptyFileException.class)
	public ResponseEntity<AppResponse> emptyFileException(EmptyFileException exception) {
		return new ResponseEntity<>(new AppResponse(true, exception.getMessage(), "File Upload Error"),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = TeacherException.class)
	public ResponseEntity<AppResponse> teacherException(TeacherException exception) {
		return new ResponseEntity<>(new AppResponse(true, exception.getMessage(), "Error In Teacher Console"),
				HttpStatus.BAD_REQUEST);
	}
}
