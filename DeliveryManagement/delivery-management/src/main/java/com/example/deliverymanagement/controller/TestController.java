package com.example.deliverymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.deliverymanagement.entity.DeliveryDetails;
import com.example.deliverymanagement.repository.DeliveryDetailsRepository;

@RestController
public class TestController {

	@Autowired
	private DeliveryDetailsRepository deliveryDetailsRepository;
	
	public ResponseEntity<DeliveryDetails> saveDeliveryDetails (@RequestBody DeliveryDetails deliveryDetails) {
		return ResponseEntity.status(HttpStatus.CREATED).body(deliveryDetailsRepository.save(deliveryDetails));
	}
	
	public void maxElement() {
		
	}
	
}
