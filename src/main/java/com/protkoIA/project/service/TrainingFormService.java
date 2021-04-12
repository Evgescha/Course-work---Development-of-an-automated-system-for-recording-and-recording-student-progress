package com.protkoIA.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.protkoIA.project.entity.TrainingForm;
import com.protkoIA.project.repository.TrainingFormRepository;

@Service
public class TrainingFormService extends CrudImpl<TrainingForm> {

	public TrainingFormRepository repository;

	@Autowired
	public TrainingFormService(TrainingFormRepository repository) {
		super(repository);
		this.repository = repository;
	}


}
