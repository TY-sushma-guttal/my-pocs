package com.autobotix.dto;

import java.util.Date;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventsDto {

	@JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
	private Date eventDate;
	@NotNull(message = "Event Name Required")
	private String eventName;
	@Lob
	@NotNull(message = "Add Description")
	private String description;
}
