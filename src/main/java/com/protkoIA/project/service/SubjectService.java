package com.protkoIA.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.protkoIA.project.entity.Subject;
import com.protkoIA.project.repository.SubjectRepository;

@Service
public class SubjectService extends CrudImpl<Subject> {

	public SubjectRepository repository;

	@Autowired
	public SubjectService(SubjectRepository repository) {
		super(repository);
		this.repository = repository;
	}


}
