package com.protkoIA.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.protkoIA.project.entity.TrainingForm;

@Repository
public interface TrainingFormRepository extends JpaRepository<TrainingForm, Long>{
   
}
