package com.autobotix.service.admin;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.autobotix.dto.CategoryDto;
import com.autobotix.dto.EditCategoryDto;
import com.autobotix.entity.Category;
import com.autobotix.exceptions.CategoryEditException;
import com.autobotix.exceptions.CategoryNotFoundException;
import com.autobotix.exceptions.CourseDeleteException;
import com.autobotix.exceptions.NoDataFoundException;
import com.autobotix.exceptions.SaveUnsuccessfullException;
import com.autobotix.repository.CategoryRepository;
import com.autobotix.util.ExceptionConstants;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final ModelMapper modelMapper;
	private final CategoryRepository categoryRepository;
	
	@Override
	public Integer addCategory(CategoryDto categoryDto) {
		Category category = new Category();
		modelMapper.map(categoryDto, category);
		try {
			return categoryRepository.save(category).getCategoryId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SaveUnsuccessfullException("Category Save Unsuccessfull");
		}
	}

	@Override
	public EditCategoryDto editCategory(EditCategoryDto editCategory) {
		Optional<Category> categoryFromDb = categoryRepository.findById(editCategory.getCategoryId());
		try {
			if (categoryFromDb.isPresent()) {
				Category category = categoryFromDb.get();
				modelMapper.map(editCategory, category);
				categoryRepository.save(category);
				return editCategory;
			}
			throw new CategoryNotFoundException("Category Not Found");
		} catch (CategoryNotFoundException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CategoryEditException("Modifiacation Was Unsuccessfull");
		}
	}

	@Override
	public String deleteCategory(Integer categoryId) {
		Optional<Category> categoryFromDb = categoryRepository.findById(categoryId);
		try {
			if (categoryFromDb.isPresent()) {
				categoryRepository.delete(categoryFromDb.get());
				return "Deletion successfull";
			}
			throw new CategoryNotFoundException("No Category Found");
		} catch (CategoryNotFoundException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CourseDeleteException("Couldn't Delete the Category");
		}

	}

	@Override
	public List<Category> getAllCategory() {

		List<Category> findAll = categoryRepository.findAll();
		if (!findAll.isEmpty()) {
			return findAll;
		}
		throw new NoDataFoundException(ExceptionConstants.NO_DATA_FOUND);
	}

}
