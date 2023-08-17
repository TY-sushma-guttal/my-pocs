package com.autobotix.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.autobotix.util.ListStringConverter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CourseOnWebsite implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer courseId;
	@NotNull(message = "course name required")
	private String courseName;
	// add video(link / video)
	// add image

	@NotNull(message = "course description required")
	@Convert(converter = ListStringConverter.class)
	private List<String> courseDescription;
	
	@NotNull(message = "course price required")
	private Integer coursePrice;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "courseOnWebsite")
	private List<StudentReview> studentReviews;
}
