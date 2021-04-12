package com.protkoIA.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.protkoIA.project.entity.Evaluation;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long>{
   
}
