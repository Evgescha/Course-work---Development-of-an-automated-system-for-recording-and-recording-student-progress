package com.protkoIA.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.protkoIA.project.entity.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long>{
   
}
