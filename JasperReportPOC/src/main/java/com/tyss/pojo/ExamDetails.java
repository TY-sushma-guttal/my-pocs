package com.tyss.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode.Exclude;

@Data
@Entity
@Table(name="exam")
@AllArgsConstructor
@NoArgsConstructor
public class ExamDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer examId;
	
	private String examName;
	
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "exam_subject", 
			joinColumns = { @JoinColumn(name = "exam_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "subject_id") }
			)
	private List<SubjectDetails> subjects = new ArrayList<>();
}
