package com.autobotix.service.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.autobotix.dto.CourseDto;
import com.autobotix.dto.EditCourseOnDashBoardDto;
import com.autobotix.entity.CourseOnDashBoard;

public interface CourseOnDashboardService {
	

	Integer addCourseOnDash(List<MultipartFile> files, MultipartFile displayImg, CourseDto courseDto);

	String editCourseOnDash(EditCourseOnDashBoardDto courseOnDashBoardDto);

	String deleteCourseOnDash(Integer courseId);

	List<CourseOnDashBoard> getCoursesOnDash();

	Optional<CourseOnDashBoard> viewCourseOnDash(Integer courseId);

}
