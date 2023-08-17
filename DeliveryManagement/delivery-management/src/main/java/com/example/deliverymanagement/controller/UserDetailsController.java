package com.example.deliverymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.deliverymanagement.response.SuccessResponse;
import com.example.deliverymanagement.service.UserDetailsService;

@RestController
@RequestMapping("api/v1/user")
public class UserDetailsController {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@PostMapping("/send-otp/{emailId}")
	public ResponseEntity<SuccessResponse> sendOTP(@PathVariable String emailId) {
//		userDetailsService.sendOTP(emailId);
		return null;
	}

}
