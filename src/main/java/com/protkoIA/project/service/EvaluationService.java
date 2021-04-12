package com.protkoIA.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.protkoIA.project.entity.Evaluation;
import com.protkoIA.project.repository.EvaluationRepository;

@Service
public class EvaluationService extends CrudImpl<Evaluation> {

	public EvaluationRepository repository;

	@Autowired
	public EvaluationService(EvaluationRepository repository) {
		super(repository);
		this.repository = repository;
	}


}
