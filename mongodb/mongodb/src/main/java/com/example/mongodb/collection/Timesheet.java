package com.example.mongodb.collection;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Timesheet {
	
	private Integer timesheetId;
	
	private Integer employeeId;
	
	private Map<Date, List<LogInAndLogOutDetails>> timesheetDetails;

}
 