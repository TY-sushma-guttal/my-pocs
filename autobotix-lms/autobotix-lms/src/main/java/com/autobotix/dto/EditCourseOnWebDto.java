package com.autobotix.dto;

import java.util.List;

import javax.persistence.Convert;
import javax.validation.constraints.NotNull;

import com.autobotix.util.ListStringConverter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EditCourseOnWebDto {

	@NotNull(message = "course id required")
	private Integer courseWebId;
	@NotNull(message = "course name required")
	private String courseName;
	
	@NotNull(message = "course description required")
	@Convert(converter = ListStringConverter.class)
	private List<String> courseDescription;
	
	@NotNull(message = "course price required")
	private Integer coursePrice;
}
