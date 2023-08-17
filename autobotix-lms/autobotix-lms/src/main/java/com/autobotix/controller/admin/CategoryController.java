package com.autobotix.controller.admin;

import java.util.Arrays;

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

import com.autobotix.dto.CategoryDto;
import com.autobotix.dto.EditCategoryDto;
import com.autobotix.response.AppResponse;
import com.autobotix.service.admin.CategoryService;
import com.autobotix.util.ConstantValues;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
	
	private final CategoryService categoryService;

	@PostMapping("/")
	public ResponseEntity<AppResponse> addCategory(@RequestBody CategoryDto categoryDto) {
		Integer addCategory = categoryService.addCategory(categoryDto);
		return new ResponseEntity<>(
				AppResponse.builder().error(false).message(ConstantValues.SUCCESS).data(addCategory).build(),
				HttpStatus.OK);
	}

	@PutMapping("/")
	public ResponseEntity<AppResponse> editCategory(@RequestBody EditCategoryDto editCategory) {
		EditCategoryDto editedCategory = categoryService.editCategory(editCategory);
		if (editedCategory != null) {
			return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
					.data(Arrays.asList(editedCategory)).build(), HttpStatus.OK);
		}
		return new ResponseEntity<>(AppResponse.builder().error(true).message(ConstantValues.FAIL).data(null).build(),
				HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<AppResponse> deleteCategory(@PathVariable Integer categoryId) {
		String deleteCategory = categoryService.deleteCategory(categoryId);
		return new ResponseEntity<>(
				AppResponse.builder().error(false).message(ConstantValues.SUCCESS).data(deleteCategory).build(),
				HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<AppResponse> getAllCategory() {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(categoryService.getAllCategory()).build(), HttpStatus.OK);

	}

}
