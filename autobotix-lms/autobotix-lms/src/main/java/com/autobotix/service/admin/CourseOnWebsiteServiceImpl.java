package com.autobotix.service.admin;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.autobotix.dto.CourseWebsiteDto;
import com.autobotix.dto.EditCourseOnWebDto;
import com.autobotix.entity.CourseOnWebsite;
import com.autobotix.exceptions.CourseDeleteException;
import com.autobotix.exceptions.CourseEditException;
import com.autobotix.exceptions.CourseNotFoundException;
import com.autobotix.exceptions.NoDataFoundException;
import com.autobotix.repository.CourseOnWebsiteRepository;
import com.autobotix.util.ExceptionConstants;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseOnWebsiteServiceImpl implements CourseOnWebsiteService {

	private final ModelMapper modelMapper;
	private final CourseOnWebsiteRepository onWebsiteRepository;

	@Override
	public Integer addCourseOnWeb(CourseWebsiteDto courseWebsiteDto) {
		CourseOnWebsite onWebsite = new CourseOnWebsite();
		modelMapper.map(courseWebsiteDto, onWebsite);
		return onWebsiteRepository.save(onWebsite).getCourseId();
	}

	@Override
	public String editCourseOnWeb(EditCourseOnWebDto courseOnWebDto) {
		Optional<CourseOnWebsite> findById = onWebsiteRepository.findById(courseOnWebDto.getCourseWebId());
		try {
			if (findById.isPresent()) {
				CourseOnWebsite courseOnWebsite = findById.get();
				modelMapper.map(courseOnWebDto, courseOnWebsite);
				onWebsiteRepository.save(courseOnWebsite);
				return "Edit successful";
			}
			throw new CourseNotFoundException(ExceptionConstants.COURSE_NOT_FOUND);
		} catch (CourseNotFoundException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CourseEditException("Couldn't Edit Website Course Details");
		}
	}

	@Override
	public String deleteCourseOnWeb(Integer courseWebId) {
		Optional<CourseOnWebsite> findById = onWebsiteRepository.findById(courseWebId);
		try {
			if (findById.isPresent()) {
				CourseOnWebsite courseOnWebsite = findById.get();
				onWebsiteRepository.delete(courseOnWebsite);
				return "Delete Successfull";
			}
			throw new CourseNotFoundException(ExceptionConstants.COURSE_NOT_FOUND);
		} catch (CourseNotFoundException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CourseDeleteException("Couldn't Delete The Course On Website");
		}
	}

	@Override
	public List<CourseOnWebsite> getCoursesOnWeb() {
		List<CourseOnWebsite> findAll = onWebsiteRepository.findAll();
		if (!findAll.isEmpty()) {
			return findAll;
		}
		throw new NoDataFoundException(ExceptionConstants.NO_DATA_FOUND);
	}

	@Override
	public Optional<CourseOnWebsite> viewCourseOnWeb(Integer courseWebId) {
		Optional<CourseOnWebsite> findById = onWebsiteRepository.findById(courseWebId);
		if (findById.isPresent()) {
			return findById;
		}
		throw new CourseNotFoundException(ExceptionConstants.COURSE_NOT_FOUND);
	}
}
