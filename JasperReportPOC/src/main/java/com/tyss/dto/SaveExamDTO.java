package com.tyss.dto;

import java.util.ArrayList;
import java.util.List;

import com.tyss.pojo.ExamDetails;
import com.tyss.pojo.SubjectDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveExamDTO {
	
	private ExamDetails examDetails;
	
	private List<SubjectDetails> subjectDetailsList = new ArrayList<SubjectDetails>();

}
