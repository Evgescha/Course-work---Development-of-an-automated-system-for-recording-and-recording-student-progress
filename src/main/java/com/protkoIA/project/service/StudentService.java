package com.protkoIA.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.protkoIA.project.entity.Student;
import com.protkoIA.project.repository.StudentRepository;

@Service
public class StudentService extends CrudImpl<Student> {

	public StudentRepository repository;

	@Autowired
	public StudentService(StudentRepository repository) {
		super(repository);
		this.repository = repository;
	}


}
