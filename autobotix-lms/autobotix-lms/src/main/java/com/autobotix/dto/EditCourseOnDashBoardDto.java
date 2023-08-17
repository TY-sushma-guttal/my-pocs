package com.autobotix.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Convert;
import javax.validation.constraints.NotNull;

import com.autobotix.util.ListStringConverter;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EditCourseOnDashBoardDto {

	private Integer courseId;
	@NotNull(message = "category required")
	private String category;
	@NotNull(message = "coursename required")
	private String courseName;

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
}
