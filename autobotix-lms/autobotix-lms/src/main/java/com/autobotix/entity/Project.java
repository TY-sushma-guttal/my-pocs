package com.autobotix.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Project implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer projectId;
	private String category;
	private String courseName;
	private String organization;
	private String grade;
	private String projectName;
	// add project file as multipart
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "courseId")
	private CourseOnDashBoard courseOnDashBoard;
}
