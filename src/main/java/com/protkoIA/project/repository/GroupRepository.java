package com.protkoIA.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.protkoIA.project.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long>{
   
}
