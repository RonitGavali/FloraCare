package com.project.Flora_Care.controller;

import com.project.Flora_Care.entity.PlantDetail;
import com.project.Flora_Care.exception.PlantDetailNotFoundException;
import com.project.Flora_Care.model.PlantDetailRequest;
import com.project.Flora_Care.model.PlantDetailVO;
import com.project.Flora_Care.service.IPlantDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/plants")
public class PlantDetailController {

    @Autowired
    private IPlantDetail plantDetailService;

 // GET all plants
    @GetMapping
    public ResponseEntity<List<PlantDetailVO>> getAllPlants() {
        try {
            List<PlantDetailVO> plants = plantDetailService.findAll();
            if (plants == null || plants.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(plants, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET plant by ID
    @GetMapping("/{id}")
    public ResponseEntity<PlantDetailVO> getPlantById(@PathVariable Long id) {
        try {
            PlantDetailVO plant = plantDetailService.findById(id);
            if (plant == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(plant, HttpStatus.OK);
        } catch (PlantDetailNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @PostMapping
    public ResponseEntity<PlantDetailVO> createPlant(@RequestBody PlantDetailRequest plantDetailRequest) {
        if (plantDetailRequest == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            PlantDetailVO plantDetailVO = plantDetailService.save(plantDetailRequest);
            if (plantDetailVO == null) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(plantDetailVO, HttpStatus.CREATED);
        } catch (PlantDetailNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace(); // Add this for debugging
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping
//    public ResponseEntity<PlantDetailVO> createPlant(@RequestBody PlantDetailRequest plantDetailRequest) {
//        if (plantDetailRequest == null) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        try {
//            PlantDetailVO plantDetailVO = plantDetailService.save(plantDetailRequest);
//            if (plantDetailVO == null) {
//                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//            return new ResponseEntity<>(plantDetailVO, HttpStatus.CREATED); // Better status for resource creation
//        } catch (PlantDetailNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<PlantDetail> updatePlant(@PathVariable Long id, @RequestBody PlantDetail updatedPlant)
//            throws PlantDetailNotFoundException {
//        PlantDetail existing = plantDetailService.findById(id);
//
//        existing.setName(updatedPlant.getName());
//        existing.setScientificName(updatedPlant.getScientificName());
//        existing.setSpecies(updatedPlant.getSpecies());
//        existing.setGrowEnvironment(updatedPlant.getGrowEnvironment());
//        existing.setPrice(updatedPlant.getPrice());
//
//        PlantDetail saved = plantDetailService.save(existing);
//        return ResponseEntity.ok(saved);
//    }


 // DELETE plant by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlant(@PathVariable Long id) {
        try {
            String message = plantDetailService.delete(id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (PlantDetailNotFoundException e) {
            return new ResponseEntity<>("Plant not found with id: " + id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal server error while deleting plant", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
