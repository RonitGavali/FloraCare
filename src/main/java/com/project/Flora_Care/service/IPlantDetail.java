//package com.project.Flora_Care.service;
//
//import java.util.List;
//import java.util.UUID;
//
//import com.project.Flora_Care.exception.PlantDetailNotFoundException;
//import com.project.Flora_Care.model.PlantDetailRequest;
//import com.project.Flora_Care.model.PlantDetailVO;
//
//public interface IPlantDetail {
//
//	List<PlantDetailVO> findAll();
//
//	PlantDetailVO findById(UUID id) throws PlantDetailNotFoundException;
//
//	PlantDetailVO save(PlantDetailRequest plantDetailRequest) throws PlantDetailNotFoundException;
//
//	String delete(UUID id) throws PlantDetailNotFoundException;
//
//}
package com.project.Flora_Care.service;

import java.util.List;
import java.util.UUID;

import com.project.Flora_Care.exception.PlantDetailNotFoundException;
import com.project.Flora_Care.model.PlantDetailRequest;
import com.project.Flora_Care.model.PlantDetailVO;
import com.project.Flora_Care.entity.PlantDetail;

public interface IPlantDetail {

	List<PlantDetailVO> findAll();

	PlantDetailVO findById(Long id) throws PlantDetailNotFoundException;

	PlantDetailVO save(PlantDetailRequest plantDetailRequest);

	String delete(Long id) throws PlantDetailNotFoundException;

}
