package com.autobotix.controller;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobotix.dto.ContactUsDto;
import com.autobotix.dto.OrganizationLogin;
import com.autobotix.dto.OrganizationOtpLogin;
import com.autobotix.dto.OrganizationPasswordReset;
import com.autobotix.dto.OrganizationProfileResponse;
import com.autobotix.dto.OrganizationVerificationDto;
import com.autobotix.dto.OtpVerification;
import com.autobotix.dto.PromotionalEmailDto;
import com.autobotix.dto.RegisterOrganizationDto;
import com.autobotix.dto.SendOtp;
import com.autobotix.dto.StudentLogin;
import com.autobotix.dto.StudentOtpLogin;
import com.autobotix.dto.StudentPasswordReset;
import com.autobotix.dto.StudentProfileResponse;
import com.autobotix.dto.StudentRegisterDto;
import com.autobotix.dto.StudentVerificationDto;
import com.autobotix.entity.CourseOnWebsite;
import com.autobotix.response.AppResponse;
import com.autobotix.service.HomeService;
import com.autobotix.util.ConstantValues;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/autoBotix")
public class HomeController {

	private final HomeService homeService;

	@PostMapping("/studentRegistration")
	public ResponseEntity<AppResponse> registerStudent(@RequestBody StudentRegisterDto studentRegisterDto) {
		Integer registerStudent = homeService.registerStudent(studentRegisterDto);
		return new ResponseEntity<>(
				AppResponse.builder().error(false).message(ConstantValues.SUCCESS).data(registerStudent).build(),
				HttpStatus.OK);

	}

	@PostMapping("/verifyStudent")
	public ResponseEntity<AppResponse> verifyStudent(@RequestBody StudentVerificationDto verificationDto) {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(homeService.verifyStudent(verificationDto)).build(), HttpStatus.OK);
	}

	@PostMapping("/organizationRegistration")
	public ResponseEntity<AppResponse> registerOrganization(
			@RequestBody RegisterOrganizationDto registerOrganizationDto) {
		Integer registerOrganization = homeService.registerOrganization(registerOrganizationDto);
		return new ResponseEntity<>(
				AppResponse.builder().error(false).message(ConstantValues.SUCCESS).data(registerOrganization).build(),
				HttpStatus.OK);
	}

	@PostMapping("/verifyOrganization")
	public ResponseEntity<AppResponse> verifyOrganization(@RequestBody OrganizationVerificationDto verificationDto) {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(homeService.verifyOrganization(verificationDto)).build(), HttpStatus.OK);
	}

	@PostMapping("/otpVerification")
	public ResponseEntity<AppResponse> otpVerification(@RequestBody OtpVerification otpVerification) {
		String isVerified = homeService.otpVerification(otpVerification);
		return new ResponseEntity<>(
				AppResponse.builder().error(false).message(ConstantValues.SUCCESS).data(isVerified).build(),
				HttpStatus.OK);
	}

	@PostMapping("/studentLogin")
	public ResponseEntity<AppResponse> studentLogin(@RequestBody StudentLogin studentLogin) {
		StudentProfileResponse studentLogged = homeService.studentLogin(studentLogin);
		if (studentLogged != null) {
			return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
					.data(Arrays.asList(studentLogged)).build(), HttpStatus.OK);
		}
		return new ResponseEntity<>(AppResponse.builder().error(true).message(ConstantValues.FAIL).data(null).build(),
				HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/organizationLogin")
	public ResponseEntity<AppResponse> organizationLogin(@RequestBody OrganizationLogin organizationLogin) {
		OrganizationProfileResponse organizationLogged = homeService.organizationLogin(organizationLogin);
		if (organizationLogged != null) {
			return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
					.data(Arrays.asList(organizationLogged)).build(), HttpStatus.OK);
		}
		return new ResponseEntity<>(AppResponse.builder().error(true).message(ConstantValues.FAIL).data(null).build(),
				HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/studentOtpLogin")
	public ResponseEntity<AppResponse> studentOtpLogin(@RequestBody StudentOtpLogin studentOtpLogin) {
		StudentProfileResponse studentOtpLoginStatus = homeService.studentOtpLogin(studentOtpLogin);
		if (studentOtpLoginStatus != null) {
			return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
					.data(Arrays.asList(studentOtpLoginStatus)).build(), HttpStatus.OK);
		}
		return new ResponseEntity<>(AppResponse.builder().error(true).message(ConstantValues.FAIL).data(null).build(),
				HttpStatus.BAD_REQUEST);

	}

	@PostMapping("/organizationOtpLogin")
	public ResponseEntity<AppResponse> organizationOtpLogin(@RequestBody OrganizationOtpLogin organizationOtpLogin) {
		OrganizationProfileResponse organizationOtpLoginStatus = homeService.organizationOtpLogin(organizationOtpLogin);
		if (organizationOtpLoginStatus != null) {
			return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
					.data(Arrays.asList(organizationOtpLoginStatus)).build(), HttpStatus.OK);
		}
		return new ResponseEntity<>(AppResponse.builder().error(true).message(ConstantValues.FAIL).data(null).build(),
				HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/sendOtp")
	public ResponseEntity<AppResponse> sendOtp(@RequestBody SendOtp sendOtp) {
		String otpStatus = homeService.sendOtp(sendOtp);
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(Arrays.asList(otpStatus)).build(), HttpStatus.OK);
	}

	@PutMapping("/resetStudentPassword")
	public ResponseEntity<AppResponse> resetStudentPassword(@RequestBody StudentPasswordReset passwordReset) {
		String resetStudentPassword = homeService.resetStudentPassword(passwordReset);
		return new ResponseEntity<>(
				AppResponse.builder().error(false).message(ConstantValues.SUCCESS).data(resetStudentPassword).build(),
				HttpStatus.OK);

	}

	@PutMapping("/resetOrganizationPassword")
	public ResponseEntity<AppResponse> resetOrganizationPassword(@RequestBody OrganizationPasswordReset passwordReset) {
		String resetOrganizationPassword = homeService.resetOrganizationPassword(passwordReset);
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(resetOrganizationPassword).build(), HttpStatus.OK);
	}

	@GetMapping("/allCourseToWebsite")
	public ResponseEntity<AppResponse> getAllCourseToWebsite() {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(homeService.getAllCourseToWebsite()).build(), HttpStatus.OK);
	}

	@GetMapping("/getCourseDetail/{courseId}")
	public ResponseEntity<AppResponse> getCourseDetails(@PathVariable Integer courseId) {
		Optional<CourseOnWebsite> courseDetail = homeService.getCourseDetail(courseId);
		if (courseDetail.isPresent()) {
			return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
					.data(Arrays.asList(courseDetail)).build(), HttpStatus.OK);
		}
		return new ResponseEntity<>(AppResponse.builder().error(true).message(ConstantValues.FAIL).data(null).build(),
				HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/contactUs")
	public ResponseEntity<AppResponse> contactUs(@RequestBody ContactUsDto contactUsDto) {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(Arrays.asList(homeService.contactUs(contactUsDto))).build(), HttpStatus.OK);
	}

	@PostMapping("/promotionalEmail")
	public ResponseEntity<AppResponse> promotioanalEmail(@RequestBody PromotionalEmailDto emailDto) {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(Arrays.asList((homeService.promotionalEmail(emailDto)))).build(), HttpStatus.OK);
	}

	@GetMapping("/getGallery")
	public ResponseEntity<AppResponse> getGallery() {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(homeService.getGallery()).build(), HttpStatus.OK);
	}
	
	

}
