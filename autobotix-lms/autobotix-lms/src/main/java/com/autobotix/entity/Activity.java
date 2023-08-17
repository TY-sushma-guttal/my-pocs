package com.autobotix.entity;

import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.autobotix.util.ListStringConverter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Activity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer activityCode;
	private String activityName;
	@Lob
	private String description;
	private String duration;
	private String finalProductName;
	private String productDemo;
	@Convert(converter = ListStringConverter.class)
	private List<String> keySkillsLearned;
	@Convert(converter = ListStringConverter.class)
	private List<String> priciplesApplied;

}
