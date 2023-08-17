package com.autobotix.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Lab {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer labCode;
	private String labName;
	@Lob
	private String description;
	private String supportedAgeGroup;
	private String tenure;
	private String duration;
	
	private String labIntroVideo;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "activityCode")
	private List<Activity> activities;
	
	
	

}
