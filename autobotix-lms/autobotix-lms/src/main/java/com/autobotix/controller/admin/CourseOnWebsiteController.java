package com.autobotix.controller.admin;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobotix.dto.CourseWebsiteDto;
import com.autobotix.dto.EditCourseOnWebDto;
import com.autobotix.entity.CourseOnWebsite;
import com.autobotix.response.AppResponse;
import com.autobotix.service.admin.CourseOnWebsiteService;
import com.autobotix.util.ConstantValues;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courseOnWebsite")
public class CourseOnWebsiteController {

	private final CourseOnWebsiteService courseOnWebsiteService;

	@PostMapping("/")
	public ResponseEntity<AppResponse> addCourseOnWeb(@RequestBody CourseWebsiteDto courseWebsiteDto) {
		Integer addCourse = courseOnWebsiteService.addCourseOnWeb(courseWebsiteDto);
		return new ResponseEntity<>(
				AppResponse.builder().error(false).message(ConstantValues.SUCCESS).data(addCourse).build(),
				HttpStatus.OK);
	}

	@PutMapping("/")
	public ResponseEntity<AppResponse> editCourseOnWeb(@RequestBody EditCourseOnWebDto courseOnWebDto) {
		String editCourseOnWeb = courseOnWebsiteService.editCourseOnWeb(courseOnWebDto);
		return new ResponseEntity<>(
				AppResponse.builder().error(false).message(ConstantValues.SUCCESS).data(editCourseOnWeb).build(),
				HttpStatus.OK);
	}

	@DeleteMapping("/{courseWebId}")
	public ResponseEntity<AppResponse> deleteCourseOnWeb(@PathVariable Integer courseWebId) {
		String deleteCourseOnWeb = courseOnWebsiteService.deleteCourseOnWeb(courseWebId);
		return new ResponseEntity<>(
				AppResponse.builder().error(false).message(ConstantValues.SUCCESS).data(deleteCourseOnWeb).build(),
				HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<AppResponse> getCoursesOnWeb() {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(courseOnWebsiteService.getCoursesOnWeb()).build(), HttpStatus.OK);
	}

	@GetMapping("/viewCourse/{courseWebId}")
	public ResponseEntity<AppResponse> viewCourseOnWeb(@PathVariable Integer courseWebId) {
		Optional<CourseOnWebsite> viewCourseOnWeb = courseOnWebsiteService.viewCourseOnWeb(courseWebId);
		if (!viewCourseOnWeb.isEmpty()) {
			return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
					.data(Arrays.asList(viewCourseOnWeb.get())).build(), HttpStatus.OK);
		}
		return new ResponseEntity<>(AppResponse.builder().error(true).message(ConstantValues.FAIL).data(null).build(),
				HttpStatus.BAD_REQUEST);
	}
}
