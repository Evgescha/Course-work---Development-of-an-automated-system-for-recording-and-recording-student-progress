package com.protkoIA.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.protkoIA.project.entity.TrainingType;

@Repository
public interface TrainingTypeRepository extends JpaRepository<TrainingType, Long>{
   
}
