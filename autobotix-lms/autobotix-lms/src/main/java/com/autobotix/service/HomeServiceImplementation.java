package com.autobotix.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
import com.autobotix.emailservice.EmailService;
import com.autobotix.entity.ContactUs;
import com.autobotix.entity.CourseOnWebsite;
import com.autobotix.entity.Gallery;
import com.autobotix.entity.Organization;
import com.autobotix.entity.PromotionalEmail;
import com.autobotix.entity.Student;
import com.autobotix.exceptions.AutoBotixException;
import com.autobotix.exceptions.ContactException;
import com.autobotix.exceptions.ContactNumberPresentException;
import com.autobotix.exceptions.CourseException;
import com.autobotix.exceptions.CourseNotFoundException;
import com.autobotix.exceptions.EmailException;
import com.autobotix.exceptions.EmailPresentException;
import com.autobotix.exceptions.IncorrectPasswordException;
import com.autobotix.exceptions.OrganizationEmailException;
import com.autobotix.exceptions.OrganizationException;
import com.autobotix.exceptions.OrganizationNotFoundException;
import com.autobotix.exceptions.OtpException;
import com.autobotix.exceptions.StudentEmailException;
import com.autobotix.exceptions.StudentException;
import com.autobotix.exceptions.StudentNotFoundException;
import com.autobotix.repository.ContactUsRepository;
import com.autobotix.repository.CourseOnWebsiteRepository;
import com.autobotix.repository.GalleryRepository;
import com.autobotix.repository.OrganizationRepository;
import com.autobotix.repository.PromotionalEmailRepository;
import com.autobotix.repository.StudentRepository;
import com.autobotix.util.ExceptionConstants;
import com.autobotix.util.OTPGenerator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomeServiceImplementation implements HomeService {

	private final StudentRepository studentRepository;
	private final OrganizationRepository organizationRepository;
	private final ModelMapper modelMapper;
	private final EmailService emailService;
	private final OTPGenerator otpGenerator;
	private final CourseOnWebsiteRepository courseOnWebsiteRepository;
	private final ContactUsRepository contactUsRepository;
	private final PromotionalEmailRepository promotionalEmailRepository;
	private final GalleryRepository galleryRepository;

	@Override
	public Integer registerStudent(StudentRegisterDto studentRegisterDto) {
		Student student = new Student();
		modelMapper.map(studentRegisterDto, student);
		Optional<Student> findByEmail = studentRepository.findByEmail(student.getEmail());
		Optional<Student> findByContactNumber = studentRepository.findByContactNumber(student.getContactNumber());

		if (findByContactNumber.isPresent()) {
			throw new ContactNumberPresentException("Student Contact Number Already Exists");
		} else if (findByEmail.isPresent()) {
			throw new EmailPresentException("Student Email Already Exists");
		}

		Integer generateOtp = otpGenerator.generateOTP(student.getEmail());
		emailService.sendOtpEmail(student.getEmail(), generateOtp);

		studentRepository.save(student);
		return generateOtp;
	}

	@Override
	public String verifyStudent(StudentVerificationDto verificationDto) {
		Optional<Student> findById = studentRepository.findById(verificationDto.getId());
		if (findById.isPresent() && Boolean.FALSE.equals(findById.get().getIsVerified())
				&& Boolean.TRUE.equals(otpGenerator.verifyOTP(findById.get().getEmail(), verificationDto.getOtp()))) {
			Student student = findById.get();
			student.setIsVerified(true);
			studentRepository.save(student);
			emailService.sendWelcomeEmail(findById.get().getEmail());
			return "Successfully registered";

		}
		throw new AutoBotixException("Internal Error! Try After Sometime");

	}

	@Override
	public Integer registerOrganization(RegisterOrganizationDto registerOrganizationDto) {
		Organization organization = new Organization();
		modelMapper.map(registerOrganizationDto, organization);

		Optional<Organization> findByOrganizationEmailId = organizationRepository.findByEmail(organization.getEmail());
		Optional<Organization> findByOrganizationContactNo = organizationRepository
				.findByContactNumber(organization.getContactNumber());

		if (findByOrganizationEmailId.isPresent()) {
			throw new EmailPresentException("Organization Email Already Exists");
		} else if (findByOrganizationContactNo.isPresent()) {
			throw new ContactNumberPresentException("Organization Contact Number Already Exists");
		}

		Integer generateOtp = otpGenerator.generateOTP(organization.getEmail());
		emailService.sendOtpEmail(organization.getEmail(), generateOtp);
		organizationRepository.save(organization);
		return generateOtp;
	}

	@Override
	public String verifyOrganization(OrganizationVerificationDto verificationDto) {
		Optional<Organization> findByContactNumber = organizationRepository
				.findByContactNumber(verificationDto.getContactNumber());
		if (findByContactNumber.isPresent()
				&& Boolean.FALSE.equals(findByContactNumber.get().getIsVerified() && Boolean.TRUE.equals(
						otpGenerator.verifyOTP(findByContactNumber.get().getEmail(), verificationDto.getOtp())))) {

			Organization organization = findByContactNumber.get();
			organization.setIsVerified(true);
			organizationRepository.save(organization);
			emailService.sendWelcomeEmail(findByContactNumber.get().getEmail());
			return "Successfully registered";
		}
		throw new AutoBotixException("Internal Error! Try After Sometime");
	}

	@Override
	public String otpVerification(OtpVerification otpVerification) {

		boolean verifyOTP = otpGenerator.verifyOTP(otpVerification.getEmail(), otpVerification.getOtp());
		if (verifyOTP) {
			otpGenerator.clearOTP(otpVerification.getEmail());
			return "OTP verified successfully";
		}
		throw new OtpException("OTP doesn't match");

	}

	@Override
	public StudentProfileResponse studentLogin(StudentLogin studentLogin) {
		try {
			if (studentLogin.getUserId().contains("@")) {
				Optional<Student> findByEmail = studentRepository.findByEmail(studentLogin.getUserId());

				if (!findByEmail.isEmpty()) {
					if (studentLogin.getPassword().equals(findByEmail.get().getPassword())) {
						StudentProfileResponse profileResponse = new StudentProfileResponse();
						modelMapper.map(findByEmail.get(), profileResponse);
						return profileResponse;
					}
					throw new IncorrectPasswordException(ExceptionConstants.PASSWORD_MISMATCH);
				}
				throw new StudentEmailException("Student Email Not Found");
			} else {
				Optional<Student> findById = studentRepository.findById(studentLogin.getUserId());
				if (findById.isPresent()) {

					if (studentLogin.getPassword().equals(findById.get().getPassword())) {
						StudentProfileResponse profileResponse = new StudentProfileResponse();
						modelMapper.map(findById.get(), profileResponse);
						return profileResponse;
					}
					throw new IncorrectPasswordException(ExceptionConstants.PASSWORD_MISMATCH);

				}
				throw new StudentNotFoundException("Student Id Not Found");
			}

		} catch (IncorrectPasswordException | StudentNotFoundException | StudentEmailException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new StudentException("Login Unsuccessfull! Check Your Credentials");
	}

	@Override
	public OrganizationProfileResponse organizationLogin(OrganizationLogin organizationLogin) {
		try {
			if (organizationLogin.getOrgId().contains("@")) {
				Optional<Organization> findByEmail = organizationRepository.findByEmail(organizationLogin.getOrgId());

				if (!findByEmail.isEmpty()) {
					if (organizationLogin.getPassword().equals(findByEmail.get().getPassword())) {
						OrganizationProfileResponse organizationProfile = new OrganizationProfileResponse();
						modelMapper.map(findByEmail.get(), organizationProfile);
						return organizationProfile;

					}
					throw new IncorrectPasswordException(ExceptionConstants.PASSWORD_MISMATCH);
				}
				throw new OrganizationEmailException("Organization Email Not Found");
			}

			Optional<Organization> findById = organizationRepository.findById(organizationLogin.getOrgId());

			if (findById.isPresent()) {
				if (organizationLogin.getPassword().equals(findById.get().getPassword())) {
					OrganizationProfileResponse organizationProfile = new OrganizationProfileResponse();
					modelMapper.map(findById.get(), organizationProfile);
					return organizationProfile;
				}
				throw new IncorrectPasswordException(ExceptionConstants.PASSWORD_MISMATCH);
			}
			throw new OrganizationNotFoundException("Organization Not Found");

		} catch (IncorrectPasswordException | OrganizationEmailException | OrganizationNotFoundException e) {
			throw e;

		} catch (Exception e) {
			e.printStackTrace();
		}

		throw new OrganizationException("Login Unsuccessfull! Check Your Credentials");

	}

	@Override
	public StudentProfileResponse studentOtpLogin(StudentOtpLogin studentOtpLogin) {
		try {
			boolean verifyOTP = otpGenerator.verifyOTP(studentOtpLogin.getEmail(), studentOtpLogin.getOtp());
			if (verifyOTP) {
				Optional<Student> findByEmail = studentRepository.findByEmail(studentOtpLogin.getEmail());
				if (findByEmail.isPresent()) {
					StudentProfileResponse profileResponse = new StudentProfileResponse();
					modelMapper.map(findByEmail.get(), profileResponse);
					return profileResponse;
				}
				throw new StudentNotFoundException("Student Info Not Found");
			}
			throw new OtpException("Invalid Otp");
		} catch (StudentNotFoundException | OtpException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new StudentException(ExceptionConstants.SOMETHING_WENT_WRONG_TRY_AFTER_SOMETIME);
		}
	}

	@Override
	public OrganizationProfileResponse organizationOtpLogin(OrganizationOtpLogin organizationOtpLogin) {
		try {
			boolean verifyOTP = otpGenerator.verifyOTP(organizationOtpLogin.getEmail(), organizationOtpLogin.getOtp());
			if (verifyOTP) {
				Optional<Organization> findByEmail = organizationRepository
						.findByEmail(organizationOtpLogin.getEmail());
				if (findByEmail.isPresent()) {
					OrganizationProfileResponse profileResponse = new OrganizationProfileResponse();
					modelMapper.map(findByEmail.get(), profileResponse);
					return profileResponse;
				}
				throw new OrganizationNotFoundException("Organization Info Not Found");
			}
			throw new OtpException("Invalid Otp");
		} catch (OrganizationNotFoundException | OtpException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OrganizationException(ExceptionConstants.SOMETHING_WENT_WRONG_TRY_AFTER_SOMETIME);
		}
	}

	@Override
	public String sendOtp(SendOtp sendOtp) {
		Integer generateOTP = otpGenerator.generateOTP(sendOtp.getEmail());
		if (generateOTP != null) {
			emailService.sendEmail(sendOtp.getEmail(), "OTP for email verification is " + generateOTP,
					"Email Verification");
			return "Otp sent successfully";
		}
		throw new OtpException(ExceptionConstants.SOMETHING_WENT_WRONG);
	}

	@Override
	public String resetStudentPassword(StudentPasswordReset passwordReset) {

		Optional<Student> findByEmail = studentRepository.findByEmail(passwordReset.getEmail());
		if (findByEmail.isPresent()) {
			Student student = findByEmail.get();
			student.setPassword(passwordReset.getPassword());
			studentRepository.save(student);
			return "Password reset successfull";
		}

		throw new StudentException("Password Reset Unsuccessfull");
	}

	@Override
	public String resetOrganizationPassword(OrganizationPasswordReset passwordReset) {

		Optional<Organization> findByEmail = organizationRepository.findByEmail(passwordReset.getEmail());
		if (findByEmail.isPresent()) {
			Organization organization = findByEmail.get();
			organization.setPassword(passwordReset.getPassword());
			organizationRepository.save(organization);
			return "password reset successfull";
		}
		throw new OrganizationException("Password Reset Unsuccessfull");
	}

	@Override
	public List<CourseOnWebsite> getAllCourseToWebsite() {
		return courseOnWebsiteRepository.findAll();
	}

	@Override
	public Optional<CourseOnWebsite> getCourseDetail(Integer courseId) {
		try {
			Optional<CourseOnWebsite> findById = courseOnWebsiteRepository.findById(courseId);
			if (findById.isPresent()) {
				return findById;
			}
			throw new CourseNotFoundException("Course Info Not Found");
		} catch (Exception e) {
			e.printStackTrace();
			throw new CourseException(ExceptionConstants.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public String contactUs(ContactUsDto contactUsDto) {
		try {
			ContactUs contactUs = new ContactUs();
			modelMapper.map(contactUsDto, contactUs);
			contactUsRepository.save(contactUs);
			return "Someone from the team will contact you soon";
		} catch (Exception e) {
			e.printStackTrace();
			throw new ContactException(ExceptionConstants.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public String promotionalEmail(PromotionalEmailDto emailDto) {
		try {
			PromotionalEmail email = new PromotionalEmail();
			BeanUtils.copyProperties(emailDto, email);
			promotionalEmailRepository.save(email);
			return "email updated";
		} catch (EmailException e) {
			e.printStackTrace();
			throw new EmailException(ExceptionConstants.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public List<Gallery> getGallery() {
		return galleryRepository.findAllByOrderByDateDesc();
		
	}

}
