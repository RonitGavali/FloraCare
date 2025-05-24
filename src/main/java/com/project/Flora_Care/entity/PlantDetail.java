package com.project.Flora_Care.entity;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.Flora_Care.helper.GrowEnvironment;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "plant_detail")
public class PlantDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String scientificName;
	
	private String species;
	
	
	@Enumerated(EnumType.STRING)
	private GrowEnvironment growEnvironment;
	
	
	
	private double price;
	
	@OneToMany(mappedBy = "plantDetail",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonManagedReference
	private List<PlantCareLog>plantCareLogs; 
	
	
	
	
	

}
