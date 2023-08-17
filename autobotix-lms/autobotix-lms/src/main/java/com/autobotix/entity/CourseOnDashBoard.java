package com.autobotix.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.autobotix.util.ListStringConverter;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CourseOnDashBoard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer courseId;
//	@NotNull(message = "category required")
//	private String category;

	@Convert(converter = ListStringConverter.class)
	private List<String> grade;
	@NotNull(message = "coursename required")
	private String courseName;

	private String dispImageString;

	@Lob
	@Convert(converter = ListStringConverter.class)
	private List<String> files;
	

	@NotNull(message = "description required")
	@Convert(converter = ListStringConverter.class)
	private List<String> courseDescription;
	@NotNull(message = "course description required")
	private Integer coursePrice;
	private Integer discount;
	@NotNull(message = "date required")
	@JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
	private LocalDate enrolledDate;
	@NotNull(message = "duration required")
	private String duration;
	@NotNull(message = "level required")
	private String level;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoryId")
	private Category category;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "courseOnDashBoard")
	private Set<Project> projects = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
    private Student student;
}
