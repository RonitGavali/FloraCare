package com.project.Flora_Care.service;


import com.project.Flora_Care.entity.PlantCareLog;
import com.project.Flora_Care.exception.PlantDetailNotFoundException;
import com.project.Flora_Care.model.PlantCareLogRequest;
import com.project.Flora_Care.model.PlantCareLogVO;

import java.util.List;
import java.util.UUID;

public interface IPlantCareLog {

    List<PlantCareLogVO> findAll();

    PlantCareLogVO findById(Long id);

    PlantCareLogVO save(Long plantId, PlantCareLogRequest plantCareLogRequest) throws PlantDetailNotFoundException;

    String delete(Long id);
}
