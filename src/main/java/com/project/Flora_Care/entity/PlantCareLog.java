package com.project.Flora_Care.entity;

import jakarta.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="plantCareLog")
public class PlantCareLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String careType;

	private String wateringFrequency;

	private String fertilizerSchedule;
	
	private String notes;
	
	private String performedBy;
	
	
	
	@ManyToOne
	@JoinColumn(name = "plant_id",nullable = false)
	@JsonBackReference
	private PlantDetail plantDetail;

}
