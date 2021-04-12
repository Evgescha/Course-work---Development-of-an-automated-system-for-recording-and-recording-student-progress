package com.protkoIA.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.protkoIA.project.entity.Group;
import com.protkoIA.project.repository.GroupRepository;

@Service
public class GroupService extends CrudImpl<Group> {

	public GroupRepository repository;

	@Autowired
	public GroupService(GroupRepository repository) {
		super(repository);
		this.repository = repository;
	}


}
