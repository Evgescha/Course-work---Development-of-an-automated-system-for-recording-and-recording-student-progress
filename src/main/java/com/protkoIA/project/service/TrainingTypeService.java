package com.protkoIA.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.protkoIA.project.entity.TrainingType;
import com.protkoIA.project.repository.TrainingTypeRepository;

@Service
public class TrainingTypeService extends CrudImpl<TrainingType> {

	public TrainingTypeRepository repository;

	@Autowired
	public TrainingTypeService(TrainingTypeRepository repository) {
		super(repository);
		this.repository = repository;
	}


}
