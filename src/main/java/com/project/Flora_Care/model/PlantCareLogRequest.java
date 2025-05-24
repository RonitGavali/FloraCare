package com.project.Flora_Care.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlantCareLogRequest {
	
	private Long id;

	private String careType;

	private String wateringFrequency;

	private String fertilizerSchedule;
	
	private String notes;
	
	private String performedBy;
	
	
	
	

}
