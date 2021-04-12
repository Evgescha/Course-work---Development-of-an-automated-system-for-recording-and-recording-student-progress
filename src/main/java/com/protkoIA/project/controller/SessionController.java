package com.protkoIA.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.protkoIA.project.entity.Session;
import com.protkoIA.project.service.EvaluationService;
import com.protkoIA.project.service.GroupService;
import com.protkoIA.project.service.SessionService;
import com.protkoIA.project.service.SubjectService;

@Controller
@RequestMapping("/session")
public class SessionController {

	@Autowired
	SessionService service;

	@Autowired
	GroupService serviceGroup;

	@Autowired
	SubjectService serviceSubject;

	@Autowired
	EvaluationService serviceEvaluation;

	@GetMapping
	String getCategory(Model model) {
		List<Session> list = service.repository.findAll();
		model.addAttribute("list", list);
		return "session-list";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String edit(Model model, @PathVariable(name = "id", required = false) Long id) {
		if (id != null) {
			Session entity = service.read(id);
			model.addAttribute("entity", entity);
		} else {
			model.addAttribute("entity", new Session());
		}
		model.addAttribute("groups", serviceGroup.repository.findAll());
		return "session-add-edit";
	}

	@RequestMapping(path = "/get/{id}")
	public String get(Model model, @PathVariable("id") Long id) throws Exception {
		model.addAttribute("entity", service.read(id));
		return "session-info";
	}

	@RequestMapping(path = "/delete/{id}")
	public String delete(Model model, @PathVariable("id") Long id) throws Exception {
		service.delete(id);
		return "redirect:/session";
	}

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public String createOrUpdate(Session entity, 
							@Param("group_id") Long group_id) throws Exception {
		entity.setGroup(serviceGroup.read(group_id));
		service.create(entity);
		return "redirect:/session";
	}
}
