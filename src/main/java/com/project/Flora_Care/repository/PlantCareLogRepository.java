package com.project.Flora_Care.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Flora_Care.entity.PlantCareLog;


@Repository
public interface PlantCareLogRepository extends JpaRepository<PlantCareLog, Long>{

}
