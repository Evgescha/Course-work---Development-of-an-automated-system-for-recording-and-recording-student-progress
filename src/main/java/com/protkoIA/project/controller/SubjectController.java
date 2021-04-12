package com.protkoIA.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.protkoIA.project.entity.Subject;
import com.protkoIA.project.service.SubjectService;

@Controller
@RequestMapping("/subject")
public class SubjectController {

	@Autowired
	SubjectService service;

	@GetMapping
	String getCategory(Model model) {
		List<Subject> list = service.repository.findAll();
		model.addAttribute("list", list);
		return "subject-list";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String edit(Model model, @PathVariable(name = "id", required = false) Long id) {
		if (id != null) {
			Subject entity = service.read(id);
			model.addAttribute("entity", entity);
		} else {
			model.addAttribute("entity", new Subject());
		}
		return "subject-add-edit";
	}

	@RequestMapping(path = "/get/{id}")
	public String get(Model model, @PathVariable("id") Long id) throws Exception {
		model.addAttribute("entity", service.read(id));
		return "subject-info";
	}

	@RequestMapping(path = "/delete/{id}")
	public String delete(Model model, @PathVariable("id") Long id) throws Exception {
		service.delete(id);
		return "redirect:/subject";
	}

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public String createOrUpdate(Subject entity) throws Exception {
		service.create(entity);
		return "redirect:/subject";
	}
}
