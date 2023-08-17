package com.tyss.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerClass {
	
	@GetMapping("/get")
	public String get() {
		return "Not Locked...!";
	}
	
	@GetMapping("/post")
	public String getAll() {
		return "Locked..!";
	}
	
	

}
