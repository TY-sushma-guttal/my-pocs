package com.autobotix.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
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
import com.autobotix.entity.Feeds;
import com.autobotix.entity.Gallery;
import com.autobotix.entity.User;
import com.autobotix.exceptions.CategoryEditException;
import com.autobotix.exceptions.CategoryNotFoundException;
import com.autobotix.exceptions.CourseDeleteException;
import com.autobotix.exceptions.CourseEditException;
import com.autobotix.exceptions.CourseNotFoundException;
import com.autobotix.exceptions.DeleteException;
import com.autobotix.exceptions.EmptyFileException;
import com.autobotix.exceptions.FeedException;
import com.autobotix.exceptions.FeedNotFoundException;
import com.autobotix.exceptions.NoDataFoundException;
import com.autobotix.exceptions.SaveUnsuccessfullException;
import com.autobotix.exceptions.UserNotFoundException;
import com.autobotix.exceptions.UserNotSavedException;
import com.autobotix.exceptions.UserStatusException;
import com.autobotix.firebase.DeleteFromCloud;
import com.autobotix.firebase.UploadToCloud;
import com.autobotix.repository.CategoryRepository;
import com.autobotix.repository.CourseOnDashBoardRepository;
import com.autobotix.repository.CourseOnWebsiteRepository;
import com.autobotix.repository.FeedRepository;
import com.autobotix.repository.GalleryRepository;
import com.autobotix.repository.UserRepository;
import com.autobotix.util.ExceptionConstants;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImplementation implements AdminService {

	private final CourseOnDashBoardRepository dashBoardRepository;
	private final CourseOnWebsiteRepository onWebsiteRepository;
	private final CategoryRepository categoryRepository;
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private final FeedRepository feedRepository;
	private final UploadToCloud toCloud;
	private final DeleteFromCloud deleteFromCloud;
	private final GalleryRepository galleryRepository;

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

	/* Course */
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

	/* Course on website */

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

	/* Assign user */

	@Override
	public Integer addUser(UserDto userDto) {
		User user = new User();
		modelMapper.map(userDto, user);

		try {
			return userRepository.save(user).getUserId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserNotSavedException("Couldn't Save The User");
		}
	}

	@Override
	public Optional<User> viewUser(Integer userId) {
		Optional<User> findById = userRepository.findById(userId);
		if (findById.isPresent()) {
			return findById;
		}
		throw new UserNotFoundException("User Not Present");
	}

	@Override
	public List<User> getAllUsers() {
		List<User> findAll = userRepository.findAll();
		if (!findAll.isEmpty()) {
			return findAll;
		}
		throw new NoDataFoundException(ExceptionConstants.NO_DATA_FOUND);
	}

	@Override
	public String updateUserStatus(UpdateUserStatusDto userStatusDto) {
		Optional<User> findById = userRepository.findById(userStatusDto.getUserId());
		try {
			if (findById.isPresent()) {
				User user = findById.get();
				if (userStatusDto.getStatus().equals(Boolean.TRUE) && user.getStatus().equals(Boolean.FALSE)) {
					user.setStatus(userStatusDto.getStatus());
					return "User is activated";
				} else if (userStatusDto.getStatus().equals(Boolean.FALSE) && user.getStatus().equals(Boolean.TRUE)) {
					user.setStatus(userStatusDto.getStatus());
					return "User is deactivated";
				}
				throw new UserStatusException("Couldn't Update User Status");
			}

		} catch (UserStatusException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new UserNotFoundException("No User Found");
	}

	/* feeds */
	
	@Override
	public String addFeed(MultipartFile image, FeedsDto feedsDto) {
		try {
			String uploadImage = null;
			if (image.isEmpty()) {
				throw new EmptyFileException("No File Found");
			}
			uploadImage = toCloud.uploadImage(image);
			Feeds feeds = new Feeds();
			BeanUtils.copyProperties(feedsDto, feeds);
			feeds.setImageLink(uploadImage);
			feedRepository.save(feeds);
			return "feed added";
		} catch (FeedException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public FeedResponse viewFeed(Integer id) {
		Optional<Feeds> findById = feedRepository.findById(id);
		if (findById.isPresent()) {
			FeedResponse feedDto = new FeedResponse();
			modelMapper.map(findById.get(), feedDto);
			return feedDto;
		}
		throw new FeedNotFoundException("No Feed Found");
	}

	@Override
	public String deleteFeed(Integer id) {
		try {
			Optional<Feeds> findById = feedRepository.findById(id);
			if (findById.isPresent()) {
				feedRepository.delete(findById.get());
				return "Feed deleted";
			}
			throw new FeedNotFoundException("No Feed Found");
		} catch (Exception e) {
			e.printStackTrace();
			throw new FeedException("Something Wrong In Feed");
		}
	}

	/* gallery */
	
	@Override
	public String addToGallery(List<MultipartFile> galleryImages, GalleryDto galleryDto) {
		if (galleryImages.isEmpty()) {
			throw new EmptyFileException("No File Found");
		}

		List<String> uploadedImages = new ArrayList<>();
		for (MultipartFile multipartFile : galleryImages) {
			uploadedImages.add(toCloud.uploadImage(multipartFile));
		}
		for (String string : uploadedImages) {
			System.err.println(string);
		}
		Gallery gallery = new Gallery();
		System.err.println("Gallery object created");
		gallery.setImages(uploadedImages);
		System.err.println("uploaded images set to setImages");
		gallery.setDate(galleryDto.getDate());
		
		galleryRepository.save(gallery);
		System.err.println("data saved in gallery");
		return "photos added successfully";

	}

	@Override
	public String deleteImage(DeleteImageDto deleteImage) {
		if (deleteImage == null) {
			throw new EmptyFileException("No File Found");
		}
		if (deleteFromCloud.deleteFile(deleteImage.getPath())) {
			return "delete successfull";
		}
		throw new DeleteException("Delete Unsuccessfull");
	}

}
