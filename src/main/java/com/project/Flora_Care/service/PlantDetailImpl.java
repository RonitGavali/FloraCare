package com.project.Flora_Care.service;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Flora_Care.entity.PlantDetail;
import com.project.Flora_Care.exception.PlantDetailNotFoundException;
import com.project.Flora_Care.model.PlantCareLogVO;
import com.project.Flora_Care.model.PlantDetailRequest;
import com.project.Flora_Care.model.PlantDetailVO;
import com.project.Flora_Care.repository.PlantDetailRepository;


@Service
public class PlantDetailImpl implements IPlantDetail {

	@Autowired
	private PlantDetailRepository plantDetailRepository;

	

	@Override
	public List<PlantDetailVO> findAll() {
	    List<PlantDetail> plantDetails = plantDetailRepository.findAll();

	    List<PlantDetailVO> plantDetailVOs = plantDetails.parallelStream().map(plantDetail -> {
	        PlantDetailVO plantDetailVO = new PlantDetailVO();
	        plantDetailVO.setId(plantDetail.getId());
	        plantDetailVO.setName(plantDetail.getName());
	        plantDetailVO.setScientificName(plantDetail.getScientificName());
	        plantDetailVO.setSpecies(plantDetail.getSpecies());
	        plantDetailVO.setGrowEnvironment(plantDetail.getGrowEnvironment());
	        plantDetailVO.setPrice(plantDetail.getPrice());

	        // Map PlantCareLogs to PlantCareLogVO
	        if (plantDetail.getPlantCareLogs() != null) {
	            List<PlantCareLogVO> careLogVOs = plantDetail.getPlantCareLogs().stream().map(log -> {
	                PlantCareLogVO logVO = new PlantCareLogVO();
	                logVO.setId(log.getId());
	                logVO.setCareType(log.getCareType());
	                logVO.setWateringFrequency(log.getWateringFrequency());
	                logVO.setFertilizerSchedule(log.getFertilizerSchedule());
	                logVO.setNotes(log.getNotes());
	                logVO.setPerformedBy(log.getPerformedBy());
	                logVO.setPlantDetailId(plantDetail.getId()); // set from parent
	                return logVO;
	            }).collect(Collectors.toList());

	            plantDetailVO.setPlantCareLogs(careLogVOs);
	        }

	        return plantDetailVO;
	    }).collect(Collectors.toList());

	    return plantDetailVOs;
	}


	@Override
	public PlantDetailVO findById(Long id) throws PlantDetailNotFoundException {
	    Optional<PlantDetail> optionalPlantDetail = plantDetailRepository.findById(id);

	    if (optionalPlantDetail.isEmpty()) {
	        throw new PlantDetailNotFoundException("Plant not found with ID: " + id);
	    }

	    PlantDetail plantDetail = optionalPlantDetail.get();

	    PlantDetailVO plantDetailVO = new PlantDetailVO();
	    plantDetailVO.setId(plantDetail.getId());
	    plantDetailVO.setName(plantDetail.getName());
	    plantDetailVO.setScientificName(plantDetail.getScientificName());
	    plantDetailVO.setSpecies(plantDetail.getSpecies());
	    plantDetailVO.setGrowEnvironment(plantDetail.getGrowEnvironment());
	    plantDetailVO.setPrice(plantDetail.getPrice());

	    if (plantDetail.getPlantCareLogs() != null) {
	        List<PlantCareLogVO> careLogVOs = plantDetail.getPlantCareLogs().stream().map(log -> {
	            PlantCareLogVO logVO = new PlantCareLogVO();
	            logVO.setId(log.getId());
	            logVO.setCareType(log.getCareType());
	            logVO.setWateringFrequency(log.getWateringFrequency());
	            logVO.setFertilizerSchedule(log.getFertilizerSchedule());
	            logVO.setNotes(log.getNotes());
	            logVO.setPerformedBy(log.getPerformedBy());
	            logVO.setPlantDetailId(plantDetail.getId());
	            return logVO;
	        }).collect(Collectors.toList());

	        plantDetailVO.setPlantCareLogs(careLogVOs);
	    }

	    return plantDetailVO;
	}
    
	    @Override
	    public PlantDetailVO save(PlantDetailRequest plantDetailRequest) {
	        if (plantDetailRequest == null) {
	            throw new PlantDetailNotFoundException("Invalid Request.");
	        }

	        PlantDetail plantDetail = new PlantDetail();

	        if (plantDetailRequest.getId() != null && plantDetailRequest.getId() > 0) {
	            plantDetail.setId(plantDetailRequest.getId());
	        }
	        plantDetail.setName(plantDetailRequest.getName());
	        plantDetail.setPrice(plantDetailRequest.getPrice());
	        plantDetail.setGrowEnvironment(plantDetailRequest.getGrowEnvironment());
	        plantDetail.setScientificName(plantDetailRequest.getScientificName());
	        plantDetail.setSpecies(plantDetailRequest.getSpecies());

	        // Do NOT set plantCareLogs here for now unless coming from the request

	        PlantDetail savedPlant = plantDetailRepository.save(plantDetail);

	        PlantDetailVO plantDetailVO = null;
	        if (savedPlant != null) {
	            plantDetailVO = new PlantDetailVO();
	            plantDetailVO.setId(savedPlant.getId());
	            plantDetailVO.setGrowEnvironment(savedPlant.getGrowEnvironment());
	            plantDetailVO.setName(savedPlant.getName());
	            plantDetailVO.setPrice(savedPlant.getPrice());
	            plantDetailVO.setScientificName(savedPlant.getScientificName());
	            plantDetailVO.setSpecies(savedPlant.getSpecies());
	            // Optionally plantCareLogs mapping if needed
	        }

	        return plantDetailVO;
	    }
	    
	    


	    @Override
	    public String delete(Long id) throws PlantDetailNotFoundException {
	       
	    	
	    	if(id < 0) {
	    		throw new PlantDetailNotFoundException("Invalid PlantDetail Id");
	    	}
	    	try {
	    		plantDetailRepository.deleteById(id);
	    	}catch (Exception e) {
				throw new PlantDetailNotFoundException("Exception while deleting Plant Deatil.");
			}
	    	
	        return "Plant detail with ID " + id + " has been deleted.";
	    }
	}

