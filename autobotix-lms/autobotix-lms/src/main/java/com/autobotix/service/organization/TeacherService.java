package com.autobotix.service.organization;

import java.util.List;

import com.autobotix.dto.EditTeacherDto;
import com.autobotix.dto.TeacherDto;
import com.autobotix.entity.Teacher;

public interface TeacherService {

	String addTeacher(TeacherDto teacherDto);

	String editTeacher(EditTeacherDto editTeacherDto);

	String deleteTeacher(Integer id);

	List<Teacher> getAllTeacher();

}
