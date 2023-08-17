package com.tyss.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.dto.SaveExamDTO;
import com.tyss.dto.StudentDTO;
import com.tyss.pojo.ExamDetails;
import com.tyss.service.JasperReportService;
import com.tyss.service.StudentService;
import com.tyss.utils.SuccessResponse;

@RestController
public class JasperReportController {

	@Autowired
	private JasperReportService service;
	
	@Autowired
	private StudentService studentService;

	@PostMapping("/save")
	public ResponseEntity<SuccessResponse> saveStudent(@RequestBody StudentDTO studentDTO) {

		StudentDTO result = service.saveStudent(studentDTO);
		SuccessResponse successResponse = null;
		if (result != null) {
			successResponse = new SuccessResponse("saved", result);
			return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.CREATED);
		} else {
			successResponse = new SuccessResponse("Failed to save", result);
			return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/get")
	public ResponseEntity<SuccessResponse> getAllStudent() {
		List<StudentDTO>result = service.getAllStudent();
		SuccessResponse successResponse = null;
		if (!result.isEmpty()) {
			successResponse = new SuccessResponse("Fetched", result);
			return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.CREATED);
		} else {
			successResponse = new SuccessResponse("Failed to fetch", result);
			return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping("/download")
	public String downloadPdf(HttpServletResponse response) throws Exception {
		return service.exportReport(response);
	}
	
	
	
	@PostMapping("/add/examn")
	public ResponseEntity<SuccessResponse> addExams(@RequestBody SaveExamDTO examDetails) {
		ExamDetails result = studentService.addExam(examDetails);
		SuccessResponse successResponse = null;
		if (result!=null) {
			successResponse = new SuccessResponse("Exams Added", result);
			return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.CREATED);
		} else {
			successResponse = new SuccessResponse("Failed to add Exams", result);
			return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.NOT_FOUND);
		}

	}

}
