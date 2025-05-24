package com.project.Flora_Care.service;

import com.project.Flora_Care.entity.PlantCareLog;
import com.project.Flora_Care.entity.PlantDetail;
import com.project.Flora_Care.exception.PlantCareLogNotFoundException;
import com.project.Flora_Care.exception.PlantDetailNotFoundException;
import com.project.Flora_Care.model.PlantCareLogRequest;
import com.project.Flora_Care.model.PlantCareLogVO;
import com.project.Flora_Care.repository.PlantCareLogRepository;
import com.project.Flora_Care.repository.PlantDetailRepository;
import com.project.Flora_Care.service.IPlantCareLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PlantCareLogServiceImpl implements IPlantCareLog {

    @Autowired
    private PlantCareLogRepository plantCareLogRepository;

    @Autowired
    private PlantDetailRepository plantDetailRepository;

    @Override
    public List<PlantCareLogVO> findAll() {
        List<PlantCareLog> plantCareLogs = plantCareLogRepository.findAll();

        List<PlantCareLogVO> careLogVOs = plantCareLogs.stream().map(log -> {
            PlantCareLogVO logVO = new PlantCareLogVO();
            logVO.setId(log.getId());
            logVO.setCareType(log.getCareType());
            logVO.setWateringFrequency(log.getWateringFrequency());
            logVO.setFertilizerSchedule(log.getFertilizerSchedule());
            logVO.setNotes(log.getNotes());
            logVO.setPerformedBy(log.getPerformedBy());
            logVO.setPlantDetailId(log.getPlantDetail().getId());
            return logVO;
        }).collect(Collectors.toList());

        return careLogVOs;
    }


    @Override
    public PlantCareLogVO findById(Long id) {
        PlantCareLog log = plantCareLogRepository.findById(id)
            .orElseThrow(() -> new PlantCareLogNotFoundException("PlantCareLog not found with ID: " + id));

        PlantCareLogVO logVO = new PlantCareLogVO();
        logVO.setId(log.getId());
        logVO.setCareType(log.getCareType());
        logVO.setWateringFrequency(log.getWateringFrequency());
        logVO.setFertilizerSchedule(log.getFertilizerSchedule());
        logVO.setNotes(log.getNotes());
        logVO.setPerformedBy(log.getPerformedBy());
        logVO.setPlantDetailId(log.getPlantDetail().getId());

        return logVO;
    }

    @Override
    public String delete(Long id) {
        plantCareLogRepository.deleteById(id);
        return "Plant care log deleted successfully with ID: " + id;
    }

    @Override
    public PlantCareLogVO save(Long plantId, PlantCareLogRequest req) throws PlantDetailNotFoundException {
        PlantDetail plantDetail = plantDetailRepository.findById(plantId)
            .orElseThrow(() -> new PlantDetailNotFoundException("PlantDetail not found with ID: " + plantId));

        PlantCareLog log = new PlantCareLog();
        log.setCareType(req.getCareType());
        log.setWateringFrequency(req.getWateringFrequency());
        log.setFertilizerSchedule(req.getFertilizerSchedule());
        log.setNotes(req.getNotes());
        log.setPerformedBy(req.getPerformedBy());
        log.setPlantDetail(plantDetail);

        PlantCareLog savedLog = plantCareLogRepository.save(log);

        PlantCareLogVO logVO = new PlantCareLogVO();
        logVO.setId(savedLog.getId());
        logVO.setCareType(savedLog.getCareType());
        logVO.setWateringFrequency(savedLog.getWateringFrequency());
        logVO.setFertilizerSchedule(savedLog.getFertilizerSchedule());
        logVO.setNotes(savedLog.getNotes());
        logVO.setPerformedBy(savedLog.getPerformedBy());
        logVO.setPlantDetailId(plantId);

        return logVO;
    }
}

   