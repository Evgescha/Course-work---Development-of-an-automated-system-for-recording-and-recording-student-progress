package com.protkoIA.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.protkoIA.project.entity.Group;
import com.protkoIA.project.service.GroupService;
import com.protkoIA.project.service.SessionService;
import com.protkoIA.project.service.StudentService;
import com.protkoIA.project.service.SubjectService;

@Controller
@RequestMapping(path = { "", "/" })
public class IndexController {
	@Autowired
	SessionService serviceSession;

	@Autowired
	SubjectService serviceSubject;

	@Autowired
	StudentService serviceStudent;

	@Autowired
	GroupService serviceGroup;
	
	@GetMapping
	String getindex(Model model) {
		model.addAttribute("groups", serviceGroup.repository.findAll());
		model.addAttribute("students", serviceStudent.repository.findAll());
		return "index";
	}
}
