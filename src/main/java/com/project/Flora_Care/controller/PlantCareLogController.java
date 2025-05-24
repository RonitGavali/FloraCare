package com.project.Flora_Care.controller;

import com.project.Flora_Care.exception.PlantCareLogNotFoundException;
import com.project.Flora_Care.exception.PlantDetailNotFoundException;
import com.project.Flora_Care.model.PlantCareLogRequest;
import com.project.Flora_Care.model.PlantCareLogVO;
import com.project.Flora_Care.model.PlantDetailRequest;
import com.project.Flora_Care.model.PlantDetailVO;
import com.project.Flora_Care.service.IPlantCareLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plant-care-logs")
public class PlantCareLogController {

    @Autowired
    private IPlantCareLog plantCareLogService;

    // GET all care logs
    @GetMapping
    public ResponseEntity<List<PlantCareLogVO>> getAllCareLogs() {
        try {
            List<PlantCareLogVO> logs = plantCareLogService.findAll();
            if (logs == null || logs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(logs, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    // GET care log by ID
    @GetMapping("/{id}")
    public ResponseEntity<PlantCareLogVO> getCareLogById(@PathVariable Long id) {
        try {
            PlantCareLogVO log = plantCareLogService.findById(id);
            if (log == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(log, HttpStatus.OK);
        } catch (PlantCareLogNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // POST create new care log for a plant
    @PostMapping("/plant/{plantId}")
    public ResponseEntity<?> createCareLog(
            @PathVariable Long plantId,
            @RequestBody PlantCareLogRequest request) {
        if (request == null) {
            return new ResponseEntity<>("Request body is missing", HttpStatus.BAD_REQUEST);
        }
        try {
            PlantCareLogVO savedLog = plantCareLogService.save(plantId, request);
            if (savedLog == null) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(savedLog, HttpStatus.CREATED);
        } catch (PlantDetailNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal server error while saving care log", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE care log by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCareLog(@PathVariable Long id) {
        try {
            String message = plantCareLogService.delete(id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (PlantCareLogNotFoundException e) {
            return new ResponseEntity<>("Care log not found with id: " + id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal server error while deleting care log", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
