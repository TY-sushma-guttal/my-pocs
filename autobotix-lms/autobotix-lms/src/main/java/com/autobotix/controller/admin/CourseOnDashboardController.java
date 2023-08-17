package com.autobotix.controller.admin;

import java.util.Arrays;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.autobotix.dto.CourseDto;
import com.autobotix.dto.EditCourseOnDashBoardDto;
import com.autobotix.entity.CourseOnDashBoard;
import com.autobotix.response.AppResponse;
import com.autobotix.service.admin.CourseOnDashboardService;
import com.autobotix.util.ConstantValues;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("courseOnDashboard")
public class CourseOnDashboardController {
	
	private final CourseOnDashboardService courseOnDashboardService;
	
	@PostMapping("/")
	public ResponseEntity<AppResponse> addCourseOnDash(@RequestParam(value = "videos") List<MultipartFile> files,
			@RequestParam("displayImg") MultipartFile displayImg,
			@RequestPart(value = "courseData") CourseDto courseDto) {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(courseOnDashboardService.addCourseOnDash(files, displayImg, courseDto)).build(), HttpStatus.OK);

	}

	@PutMapping("/")
	public ResponseEntity<AppResponse> editCourseOnDash(@RequestBody EditCourseOnDashBoardDto courseOnDashBoardDto) {
		String editCourseOnDash = courseOnDashboardService.editCourseOnDash(courseOnDashBoardDto);
		return new ResponseEntity<>(
				AppResponse.builder().error(false).message(ConstantValues.SUCCESS).data(editCourseOnDash).build(),
				HttpStatus.OK);
	}

	@DeleteMapping("/{courseId}")
	public ResponseEntity<AppResponse> deleteCourseOnDash(@PathVariable Integer courseId) {
		String deleteCourseOnDash = courseOnDashboardService.deleteCourseOnDash(courseId);
		return new ResponseEntity<>(
				AppResponse.builder().error(false).message(ConstantValues.SUCCESS).data(deleteCourseOnDash).build(),
				HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<AppResponse> getAllCoursesOnDash() {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(courseOnDashboardService.getCoursesOnDash()).build(), HttpStatus.OK);
	}

	@GetMapping("/viewCourse/{courseId}")
	public ResponseEntity<AppResponse> viewCourseOnDash(@PathVariable Integer courseId) {
		Optional<CourseOnDashBoard> viewCourseOnDash = courseOnDashboardService.viewCourseOnDash(courseId);
		if (!viewCourseOnDash.isEmpty()) {
			return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
					.data(Arrays.asList(viewCourseOnDash.get())).build(), HttpStatus.OK);
		}
		return new ResponseEntity<>(AppResponse.builder().error(true).message(ConstantValues.FAIL).data(null).build(),
				HttpStatus.BAD_REQUEST);
	}

}
