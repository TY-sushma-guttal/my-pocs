package com.autobotix.service.admin;

import java.util.List;

import com.autobotix.dto.CategoryDto;
import com.autobotix.dto.EditCategoryDto;
import com.autobotix.entity.Category;

public interface CategoryService {
	
	Integer addCategory(CategoryDto categoryDto);

	EditCategoryDto editCategory(EditCategoryDto editCategory);

	String deleteCategory(Integer categoryId);

	List<Category> getAllCategory();


}
