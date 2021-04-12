package com.protkoIA.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.protkoIA.project.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
   
}
