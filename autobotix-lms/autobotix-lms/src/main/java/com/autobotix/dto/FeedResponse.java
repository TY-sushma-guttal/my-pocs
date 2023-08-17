package com.autobotix.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedResponse {
	
	private LocalDate postDate;
	private String description;
	private String imageLink;
}
