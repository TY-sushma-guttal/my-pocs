package com.autobotix.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Convert;
import javax.validation.constraints.NotNull;

import com.autobotix.util.ListStringConverter;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CourseDto {

	@NotNull(message = "category required")
	private String category;
	@NotNull(message = "course name required")
	private String courseName;

	@NotNull(message = "course description required")
	@Convert(converter = ListStringConverter.class)
	private List<String> courseDescription;
	@NotNull(message = "price required")
	private Integer coursePrice;

	private Integer discount;
	@NotNull(message = "date required")
	@JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
	private LocalDate enrolledDate;
	@NotNull(message = "duration required")
	private String duration;
	@NotNull(message = "level required")
	private String level;
}
