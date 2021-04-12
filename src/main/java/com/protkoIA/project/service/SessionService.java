package com.protkoIA.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.protkoIA.project.entity.Session;
import com.protkoIA.project.repository.SessionRepository;

@Service
public class SessionService extends CrudImpl<Session> {

	public SessionRepository repository;

	@Autowired
	public SessionService(SessionRepository repository) {
		super(repository);
		this.repository = repository;
	}


}
