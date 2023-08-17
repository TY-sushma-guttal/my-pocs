package com.autobotix.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.autobotix.dto.CategoryDto;
import com.autobotix.dto.CourseDto;
import com.autobotix.dto.CourseWebsiteDto;
import com.autobotix.dto.DeleteImageDto;
import com.autobotix.dto.EditCategoryDto;
import com.autobotix.dto.EditCourseOnDashBoardDto;
import com.autobotix.dto.EditCourseOnWebDto;
import com.autobotix.dto.FeedResponse;
import com.autobotix.dto.FeedsDto;
import com.autobotix.dto.GalleryDto;
import com.autobotix.dto.UpdateUserStatusDto;
import com.autobotix.dto.UserDto;
import com.autobotix.entity.Category;
import com.autobotix.entity.CourseOnDashBoard;
import com.autobotix.entity.CourseOnWebsite;
import com.autobotix.entity.User;

public interface AdminService {

	Integer addCategory(CategoryDto categoryDto);

	EditCategoryDto editCategory(EditCategoryDto editCategory);

	String deleteCategory(Integer categoryId);

	List<Category> getAllCategory();

	Integer addCourseOnDash(List<MultipartFile> files, MultipartFile displayImg, CourseDto courseDto);

	String editCourseOnDash(EditCourseOnDashBoardDto courseOnDashBoardDto);

	String deleteCourseOnDash(Integer courseId);

	List<CourseOnDashBoard> getCoursesOnDash();

	Optional<CourseOnDashBoard> viewCourseOnDash(Integer courseId);

	Integer addCourseOnWeb(CourseWebsiteDto courseWebsiteDto);

	String editCourseOnWeb(EditCourseOnWebDto courseOnWebDto);

	String deleteCourseOnWeb(Integer courseWebId);

	List<CourseOnWebsite> getCoursesOnWeb();

	Optional<CourseOnWebsite> viewCourseOnWeb(Integer courseWebId);

	Integer addUser(UserDto userDto);

	Optional<User> viewUser(Integer userId);

	List<User> getAllUsers();

	String updateUserStatus(UpdateUserStatusDto userStatusDto);

	String addFeed(MultipartFile image, FeedsDto feedsDto);

	FeedResponse viewFeed(Integer id);

	String deleteFeed(Integer id);

	String addToGallery(List<MultipartFile> galleryImages, GalleryDto galleryDto);

	String deleteImage(DeleteImageDto deleteImage);

}
