package com.tyss.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode.Exclude;

@Data
@Entity
@Table(name="subject")
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer subjectId;
	
	private String subjectName;
	
	@ManyToMany(cascade = CascadeType.PERSIST,mappedBy = "subjects")
    private List<ExamDetails> exams;

}
