package com.autobotix.service.organization;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.autobotix.dto.EditTeacherDto;
import com.autobotix.dto.TeacherDto;
import com.autobotix.emailservice.EmailService;
import com.autobotix.entity.Teacher;
import com.autobotix.exceptions.NoDataFoundException;
import com.autobotix.exceptions.TeacherException;
import com.autobotix.repository.TeacherRepository;
import com.autobotix.util.OTPGenerator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

	private final ModelMapper modelMapper;
	private final EmailService emailService;
	private final OTPGenerator otpGenerator;
	private final TeacherRepository teacherRepository;

	@Override
	public String addTeacher(TeacherDto teacherDto) {
		Teacher teacher = new Teacher();
		modelMapper.map(teacherDto, teacher);
		Integer generateOTP = otpGenerator.generateOTP(teacher.getEmail());
		emailService.sendOtpEmail(teacher.getEmail(), generateOTP);

		teacherRepository.save(teacher);
		return "teacher added successfully";
	}

	@Override
	public String editTeacher(EditTeacherDto editTeacherDto) {
		try {
			Optional<Teacher> findById = teacherRepository.findById(editTeacherDto.getId());
			if (findById.isPresent()) {
				Teacher teacher = findById.get();
				modelMapper.map(editTeacherDto, teacher);
				teacherRepository.save(teacher);
				return "modification successfull";
			}
			throw new NoDataFoundException("Teacher Details Not Found");
		} catch (NoDataFoundException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TeacherException("Something Went Wrong");
		}

	}

	@Override
	public String deleteTeacher(Integer id) {
		try {
			Optional<Teacher> findById = teacherRepository.findById(id);
			if (findById.isPresent()) {
				teacherRepository.delete(findById.get());
				return "delete successfull";
			}
			throw new NoDataFoundException("Teacher Deatails Not Found");
		} catch (NoDataFoundException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TeacherException("Something Went Wrong");
		}

	}

	@Override
	public List<Teacher> getAllTeacher() {
	return teacherRepository.findAll();
	}

}
