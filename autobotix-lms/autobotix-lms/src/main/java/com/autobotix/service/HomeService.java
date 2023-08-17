package com.autobotix.service;

import java.util.List;
import java.util.Optional;

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
import com.autobotix.entity.Gallery;

public interface HomeService {

	Integer registerStudent(StudentRegisterDto studentRegisterDto);
	
	String verifyStudent(StudentVerificationDto verificationDto);

	Integer registerOrganization(RegisterOrganizationDto registerOrganizationDto);
	
	String verifyOrganization(OrganizationVerificationDto verificationDto);

	String otpVerification(OtpVerification otpVerification);

	StudentProfileResponse studentLogin(StudentLogin studentLogin);

	OrganizationProfileResponse organizationLogin(OrganizationLogin organizationLogin);

	StudentProfileResponse studentOtpLogin(StudentOtpLogin studentOtpLogin);

	OrganizationProfileResponse organizationOtpLogin(OrganizationOtpLogin organizationOtpLogin);

	String sendOtp(SendOtp sendOtp);

	String resetStudentPassword(StudentPasswordReset passwordReset);

	String resetOrganizationPassword(OrganizationPasswordReset passwordReset);

	List<CourseOnWebsite> getAllCourseToWebsite();

	Optional<CourseOnWebsite> getCourseDetail(Integer courseId);

	String contactUs(ContactUsDto contactUsDto);

	String promotionalEmail(PromotionalEmailDto emailDto);

	List<Gallery> getGallery();

	

	

}
