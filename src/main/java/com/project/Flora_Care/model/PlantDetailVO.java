package com.project.Flora_Care.model;

import java.util.List;
import java.util.UUID;

import com.project.Flora_Care.helper.GrowEnvironment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlantDetailVO {
	


	private Long id;

	private String name;

	private String scientificName;

	private String species;

	private GrowEnvironment growEnvironment;

	private double price;
	
	private List<PlantCareLogVO>plantCareLogs;

}
