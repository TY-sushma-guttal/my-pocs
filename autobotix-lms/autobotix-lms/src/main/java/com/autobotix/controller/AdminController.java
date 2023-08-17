package com.autobotix.controller;

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

import com.autobotix.dto.CategoryDto;
import com.autobotix.dto.CourseDto;
import com.autobotix.dto.CourseWebsiteDto;
import com.autobotix.dto.DeleteImageDto;
import com.autobotix.dto.EditCategoryDto;
import com.autobotix.dto.EditCourseOnDashBoardDto;
import com.autobotix.dto.EditCourseOnWebDto;
import com.autobotix.dto.FeedsDto;
import com.autobotix.dto.GalleryDto;
import com.autobotix.dto.UpdateUserStatusDto;
import com.autobotix.dto.UserDto;
import com.autobotix.entity.CourseOnDashBoard;
import com.autobotix.entity.CourseOnWebsite;
import com.autobotix.entity.User;
import com.autobotix.response.AppResponse;
import com.autobotix.service.AdminService;
import com.autobotix.util.ConstantValues;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

	private final AdminService adminService;

	/* Category */

	@PostMapping("/addCategory")
	public ResponseEntity<AppResponse> addCategory(@RequestBody CategoryDto categoryDto) {
		Integer addCategory = adminService.addCategory(categoryDto);
		return new ResponseEntity<>(
				AppResponse.builder().error(false).message(ConstantValues.SUCCESS).data(addCategory).build(),
				HttpStatus.OK);
	}

	@PutMapping("/editCategory")
	public ResponseEntity<AppResponse> editCategory(@RequestBody EditCategoryDto editCategory) {
		EditCategoryDto editedCategory = adminService.editCategory(editCategory);
		if (editedCategory != null) {
			return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
					.data(Arrays.asList(editedCategory)).build(), HttpStatus.OK);
		}
		return new ResponseEntity<>(AppResponse.builder().error(true).message(ConstantValues.FAIL).data(null).build(),
				HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/deleteCategory/{categoryId}")
	public ResponseEntity<AppResponse> deleteCategory(@PathVariable Integer categoryId) {
		String deleteCategory = adminService.deleteCategory(categoryId);
		return new ResponseEntity<>(
				AppResponse.builder().error(false).message(ConstantValues.SUCCESS).data(deleteCategory).build(),
				HttpStatus.OK);
	}

	@GetMapping("/getAllCategory")
	public ResponseEntity<AppResponse> getAllCategory() {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(adminService.getAllCategory()).build(), HttpStatus.OK);

	}

	/* Course On DashBoard */

	@PostMapping("/addCourseOnDash")
	public ResponseEntity<AppResponse> addCourseOnDash(@RequestParam(value = "videos") List<MultipartFile> files,
			@RequestParam("displayImg") MultipartFile displayImg,
			@RequestPart(value = "courseData") CourseDto courseDto) {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(adminService.addCourseOnDash(files, displayImg, courseDto)).build(), HttpStatus.OK);

	}

	@PutMapping("/editCourseOnDash")
	public ResponseEntity<AppResponse> editCourseOnDash(@RequestBody EditCourseOnDashBoardDto courseOnDashBoardDto) {
		String editCourseOnDash = adminService.editCourseOnDash(courseOnDashBoardDto);
		return new ResponseEntity<>(
				AppResponse.builder().error(false).message(ConstantValues.SUCCESS).data(editCourseOnDash).build(),
				HttpStatus.OK);
	}

	@DeleteMapping("deleteCourseOnDash/{courseId}")
	public ResponseEntity<AppResponse> deleteCourseOnDash(@PathVariable Integer courseId) {
		String deleteCourseOnDash = adminService.deleteCourseOnDash(courseId);
		return new ResponseEntity<>(
				AppResponse.builder().error(false).message(ConstantValues.SUCCESS).data(deleteCourseOnDash).build(),
				HttpStatus.OK);
	}

	@GetMapping("/getCoursesOnDash")
	public ResponseEntity<AppResponse> getAllCoursesOnDash() {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(adminService.getCoursesOnDash()).build(), HttpStatus.OK);
	}

	@PostMapping("/viewCourseOnDash/{courseId}")
	public ResponseEntity<AppResponse> viewCourseOnDash(@PathVariable Integer courseId) {
		Optional<CourseOnDashBoard> viewCourseOnDash = adminService.viewCourseOnDash(courseId);
		if (!viewCourseOnDash.isEmpty()) {
			return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
					.data(Arrays.asList(viewCourseOnDash.get())).build(), HttpStatus.OK);
		}
		return new ResponseEntity<>(AppResponse.builder().error(true).message(ConstantValues.FAIL).data(null).build(),
				HttpStatus.BAD_REQUEST);
	}

	/* Course on Website */

	@PostMapping("/addCourseOnWeb")
	public ResponseEntity<AppResponse> addCourseOnWeb(@RequestBody CourseWebsiteDto courseWebsiteDto) {
		Integer addCourse = adminService.addCourseOnWeb(courseWebsiteDto);
		return new ResponseEntity<>(
				AppResponse.builder().error(false).message(ConstantValues.SUCCESS).data(addCourse).build(),
				HttpStatus.OK);
	}

	@PutMapping("/editCourseOnWeb")
	public ResponseEntity<AppResponse> editCourseOnWeb(@RequestBody EditCourseOnWebDto courseOnWebDto) {
		String editCourseOnWeb = adminService.editCourseOnWeb(courseOnWebDto);
		return new ResponseEntity<>(
				AppResponse.builder().error(false).message(ConstantValues.SUCCESS).data(editCourseOnWeb).build(),
				HttpStatus.OK);
	}

	@DeleteMapping("deleteCourseOnWeb/{courseWebId}")
	public ResponseEntity<AppResponse> deleteCourseOnWeb(@PathVariable Integer courseWebId) {
		String deleteCourseOnWeb = adminService.deleteCourseOnWeb(courseWebId);
		return new ResponseEntity<>(
				AppResponse.builder().error(false).message(ConstantValues.SUCCESS).data(deleteCourseOnWeb).build(),
				HttpStatus.OK);
	}

	@GetMapping("/getCoursesOnWeb")
	public ResponseEntity<AppResponse> getCoursesOnWeb() {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(adminService.getCoursesOnWeb()).build(), HttpStatus.OK);
	}

	@PostMapping("/viewCourseOnWeb/{courseWebId}")
	public ResponseEntity<AppResponse> viewCourseOnWeb(@PathVariable Integer courseWebId) {
		Optional<CourseOnWebsite> viewCourseOnWeb = adminService.viewCourseOnWeb(courseWebId);
		if (!viewCourseOnWeb.isEmpty()) {
			return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
					.data(Arrays.asList(viewCourseOnWeb.get())).build(), HttpStatus.OK);
		}
		return new ResponseEntity<>(AppResponse.builder().error(true).message(ConstantValues.FAIL).data(null).build(),
				HttpStatus.BAD_REQUEST);
	}

	/* assign user operaions */

	@PostMapping("/addUser")
	public ResponseEntity<AppResponse> addUser(@RequestBody UserDto userDto) {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(adminService.addUser(userDto)).build(), HttpStatus.OK);
	}

	@PostMapping("/viewUser/{userId}")
	public ResponseEntity<AppResponse> viewUser(@PathVariable Integer userId) {
		Optional<User> viewUser = adminService.viewUser(userId);
		if (!viewUser.isEmpty()) {
			return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
					.data(Arrays.asList(viewUser.get())).build(), HttpStatus.OK);
		}
		return new ResponseEntity<>(AppResponse.builder().error(true).message(ConstantValues.FAIL).data(null).build(),
				HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/allUsers")
	public ResponseEntity<AppResponse> getAllUsers() {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(Arrays.asList(adminService.getAllUsers())).build(), HttpStatus.OK);
	}

	@PutMapping("updateUserStatus")
	public ResponseEntity<AppResponse> updateUserStatus(@RequestBody UpdateUserStatusDto userStatusDto) {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(adminService.updateUserStatus(userStatusDto)).build(), HttpStatus.OK);
	}

	/* Feeds */

	@PostMapping("addFeed")
	public ResponseEntity<AppResponse> addFeed(@RequestParam(value = "image") MultipartFile image,
			@RequestPart("data") FeedsDto feedsDto) {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(adminService.addFeed(image, feedsDto)).build(), HttpStatus.OK);
	}

	@GetMapping("viewFeeed/{id}")
	public ResponseEntity<AppResponse> viewFeed(@PathVariable Integer id) {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(adminService.viewFeed(id)).build(), HttpStatus.OK);
	}

	@DeleteMapping("deleteFeed/{id}")
	public ResponseEntity<AppResponse> deleteFeed(@PathVariable Integer id) {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(adminService.deleteFeed(id)).build(), HttpStatus.OK);
	}

	/* Gallery */

	@PostMapping("/addToGallery")
	public ResponseEntity<AppResponse> addToGallery(
			@RequestParam(value = "galleryImage") List<MultipartFile> galleryImages,
			@RequestPart("dateData") GalleryDto galleryDto) {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(adminService.addToGallery(galleryImages, galleryDto)).build(), HttpStatus.OK);
	}

	@PostMapping("/deleteImage")
	public ResponseEntity<AppResponse> deleteImage(@RequestBody DeleteImageDto deleteImage) {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(adminService.deleteImage(deleteImage)).build(), HttpStatus.OK);

	}
}
