package com.example.deliverymanagement.service;

import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.deliverymanagement.dto.MailDTO;
import com.example.deliverymanagement.dto.UserDetailsDTO;
import com.example.deliverymanagement.entity.UserDetails;
import com.example.deliverymanagement.exception.DataAlreadyExists;
import com.example.deliverymanagement.exception.EmailException;
import com.example.deliverymanagement.repository.UserDetailsRepository;

@Service
public class UserDetailsService {

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	public Integer sendOTP(UserDetailsDTO userDetailsDTO) {
		UserDetails userDetails = userDetailsRepository.findByEmailId(userDetailsDTO.getEmailId());
		if (userDetails == null) {
			int otp = new Random().nextInt(1000, 9999);
			Integer status = emailService.sendMail(MailDTO.builder().to(userDetailsDTO.getEmailId())
					.subject("OTP Verification!")
					.body("Dear " + userDetailsDTO.getUserName() + "/n" + "Please Verify the OTP: " + otp).build());
			if (status.equals(200))
				return otp;
			else
				throw new EmailException("Unable to send email");
		} else
			throw new DataAlreadyExists("User Account Alreay Exists");
	}
	
	
	
	
	


}
