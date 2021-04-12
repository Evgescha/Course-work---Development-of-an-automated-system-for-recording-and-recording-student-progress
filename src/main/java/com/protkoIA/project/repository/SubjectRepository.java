package com.protkoIA.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.protkoIA.project.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>{
   
}
