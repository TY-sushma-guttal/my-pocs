package com.autobotix.controller.organization;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobotix.dto.EditTeacherDto;
import com.autobotix.dto.TeacherDto;
import com.autobotix.response.AppResponse;
import com.autobotix.service.organization.TeacherService;
import com.autobotix.util.ConstantValues;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {

	private final TeacherService teacherService;

	@PostMapping("/")
	public ResponseEntity<AppResponse> addTeacher(@RequestBody TeacherDto teacherDto) {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(teacherService.addTeacher(teacherDto)).build(), HttpStatus.OK);
	}

	@PutMapping("/")
	public ResponseEntity<AppResponse> editTeacher(@RequestBody EditTeacherDto editTeacherDto) {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(teacherService.editTeacher(editTeacherDto)).build(), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<AppResponse> deleteTeacher(@PathVariable Integer id) {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(teacherService.deleteTeacher(id)).build(), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<AppResponse> getAllTeacher() {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(teacherService.getAllTeacher()).build(), HttpStatus.OK);
	}

}
