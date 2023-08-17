package com.autobotix.service.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.autobotix.dto.CourseDto;
import com.autobotix.dto.EditCourseOnDashBoardDto;
import com.autobotix.entity.CourseOnDashBoard;
import com.autobotix.exceptions.CourseDeleteException;
import com.autobotix.exceptions.CourseEditException;
import com.autobotix.exceptions.CourseNotFoundException;
import com.autobotix.exceptions.EmptyFileException;
import com.autobotix.exceptions.NoDataFoundException;
import com.autobotix.exceptions.SaveUnsuccessfullException;
import com.autobotix.firebase.UploadToCloud;
import com.autobotix.repository.CourseOnDashBoardRepository;
import com.autobotix.util.ExceptionConstants;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseOnDashboardServiceImpl implements CourseOnDashboardService {

	private final ModelMapper modelMapper;
	private final UploadToCloud toCloud;
	private final CourseOnDashBoardRepository dashBoardRepository;
	
	@Override
	public Integer addCourseOnDash(List<MultipartFile> files, MultipartFile displayImg, CourseDto courseDto) {

		try {
			String uploadImage = null;
			List<String> uploadedFiles = new ArrayList<>();

			if (files.isEmpty() && displayImg.isEmpty()) {
				throw new EmptyFileException("No File Found To Upload");
			}

			for (MultipartFile multipartFile : files) {
				uploadImage = toCloud.uploadImage(multipartFile);
				uploadedFiles.add(uploadImage);
			}

			String uploadImg = toCloud.uploadImage(displayImg);

			CourseOnDashBoard dashBoard = new CourseOnDashBoard();
			modelMapper.map(courseDto, dashBoard);
			dashBoard.setFiles(uploadedFiles);
			dashBoard.setDispImageString(uploadImg);

			return dashBoardRepository.save(dashBoard).getCourseId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SaveUnsuccessfullException("Course Save Unsuccessfull");
		}

	}

	@Override
	public String editCourseOnDash(EditCourseOnDashBoardDto courseOnDashBoardDto) {
		Optional<CourseOnDashBoard> findById = dashBoardRepository.findById(courseOnDashBoardDto.getCourseId());
		try {
			if (findById.isPresent()) {
				CourseOnDashBoard courseOnDashBoard = findById.get();
				modelMapper.map(courseOnDashBoardDto, courseOnDashBoard);
				dashBoardRepository.save(courseOnDashBoard);
				return "Edit Successfull";
			}
			throw new CourseNotFoundException(ExceptionConstants.COURSE_NOT_FOUND);

		} catch (CourseNotFoundException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CourseEditException("Couldn't Edit DashBoard Course Details");
		}
	}

	@Override
	public String deleteCourseOnDash(Integer courseId) {
		Optional<CourseOnDashBoard> findById = dashBoardRepository.findById(courseId);
		try {
			if (findById.isPresent()) {
				CourseOnDashBoard courseOnDashBoard = findById.get();
				dashBoardRepository.delete(courseOnDashBoard);
				return "Delete Successfull";
			}
			throw new CourseNotFoundException(ExceptionConstants.COURSE_NOT_FOUND);
		} catch (CourseNotFoundException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CourseDeleteException("Couldn't Delete The Course On Dashboard");
		}
	}

	@Override
	public List<CourseOnDashBoard> getCoursesOnDash() {
		List<CourseOnDashBoard> findAll = dashBoardRepository.findAll();
		if (!findAll.isEmpty()) {
			return findAll;
		}
		throw new NoDataFoundException(ExceptionConstants.NO_DATA_FOUND);
	}

	@Override
	public Optional<CourseOnDashBoard> viewCourseOnDash(Integer courseId) {
		Optional<CourseOnDashBoard> findById = dashBoardRepository.findById(courseId);
		if (findById.isPresent()) {
			return findById;
		}
		throw new CourseNotFoundException(ExceptionConstants.COURSE_NOT_FOUND);
	}
}
