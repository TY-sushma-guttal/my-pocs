package com.tyss.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.dao.ExamRepository;
import com.tyss.dao.StudentDetailRepository;
import com.tyss.dao.SubjectRepository;
import com.tyss.dto.SaveExamDTO;
import com.tyss.pojo.ExamDetails;
import com.tyss.pojo.SubjectDetails;

@Service
public class StudentService {

	@Autowired
	private StudentDetailRepository studentDetailRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private ExamRepository examRepository;

	public ExamDetails addExam(SaveExamDTO dto) {
		return null;
	}

}
