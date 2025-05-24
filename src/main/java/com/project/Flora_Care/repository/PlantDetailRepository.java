package com.project.Flora_Care.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Flora_Care.entity.PlantDetail;


@Repository
public interface PlantDetailRepository extends JpaRepository<PlantDetail, Long> {

}
