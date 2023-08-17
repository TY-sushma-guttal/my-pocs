package com.autobotix.service.admin;

import java.util.List;
import java.util.Optional;

import com.autobotix.dto.CourseWebsiteDto;
import com.autobotix.dto.EditCourseOnWebDto;
import com.autobotix.entity.CourseOnWebsite;

public interface CourseOnWebsiteService {
	
	Integer addCourseOnWeb(CourseWebsiteDto courseWebsiteDto);

	String editCourseOnWeb(EditCourseOnWebDto courseOnWebDto);

	String deleteCourseOnWeb(Integer courseWebId);

	List<CourseOnWebsite> getCoursesOnWeb();

	Optional<CourseOnWebsite> viewCourseOnWeb(Integer courseWebId);

}
