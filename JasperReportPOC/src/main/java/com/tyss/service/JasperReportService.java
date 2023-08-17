package com.tyss.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.tyss.dao.StudentRepository;
import com.tyss.dto.StudentDTO;
import com.tyss.pojo.Student;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Service
public class JasperReportService {

	@Autowired
	private StudentRepository studentRepository;

	public StudentDTO saveStudent(StudentDTO studentDTO) {
		Optional<Student> existingStudent = studentRepository.findById(studentDTO.getStudent_id());
		Student student = null;
		if (existingStudent.isPresent()) {
			student = existingStudent.get();
			BeanUtils.copyProperties(studentDTO, student);
			studentRepository.save(student);
		} else {
			student = new Student();
			BeanUtils.copyProperties(studentDTO, student);
			studentRepository.save(student);
		}
		BeanUtils.copyProperties(student, studentDTO);
		return studentDTO;
	}

	public List<StudentDTO> getAllStudent() {
		List<Student> studentList = studentRepository.findAll();
		List<StudentDTO> studentDTOList = new ArrayList<>();
		for (Student student : studentList) {
			StudentDTO studentDto = new StudentDTO();
			BeanUtils.copyProperties(student, studentDto);
			studentDTOList.add(studentDto);
		}
		return studentDTOList;
	}

	public String exportReport(HttpServletResponse response) throws Exception {
		
		List<Student> studentList = studentRepository.findAll();
		JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(studentList);
		
		Map<String, Object> Parameter = new HashMap<>();
		Parameter.put("Created By", "Sushma");
		Parameter.put("MyParameter", jrBeanCollectionDataSource);
		
		File file = ResourceUtils.getFile("classpath:PracticeTable.jrxml");
		
		JasperReport report = JasperCompileManager.compileReport(file.getAbsolutePath());
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, Parameter, jrBeanCollectionDataSource);
		
//		if(type.equalsIgnoreCase("xls")) {
//			JasperExportManager.exportReportToPdfFile(jasperPrint, "D:\\JasperReportWorkspace"+"\\student.xls");
//		}
		JRXlsxExporter jrXlsExporter = new JRXlsxExporter();
		SimpleXlsxReportConfiguration reportConfiguration = new SimpleXlsxReportConfiguration();
		reportConfiguration.setSheetNames(new String[] {"Sheet1"});
//		reportConfiguration.setIgnoreCellBackground(true);
//		reportConfiguration.setRemoveEmptySpaceBetweenColumns(true);
		jrXlsExporter.setConfiguration(reportConfiguration);
		jrXlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		jrXlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
		response.setHeader("Content-Disposition", "attachment;filename=jasperReport.xlsx");
		response.setContentType("application/octet-stream");
		jrXlsExporter.exportReport();
		
		return "Report is generated in mentioned path.";
		
	}
}
