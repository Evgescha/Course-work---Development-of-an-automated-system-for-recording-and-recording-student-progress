package com.protkoIA.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.protkoIA.project.entity.TrainingForm;
import com.protkoIA.project.service.TrainingFormService;

@Controller
@RequestMapping("/trainingForm")
public class TrainingFormController {

	@Autowired
	TrainingFormService service;

	@GetMapping
	String getCategory(Model model) {
		List<TrainingForm> list = service.repository.findAll();
		model.addAttribute("list", list);
		return "trainingForm-list";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String edit(Model model, @PathVariable(name = "id", required = false) Long id) {
		if (id != null) {
			TrainingForm entity = service.read(id);
			model.addAttribute("entity", entity);
		} else {
			model.addAttribute("entity", new TrainingForm());
		}
		return "trainingForm-add-edit";
	}

	@RequestMapping(path = "/get/{id}")
	public String get(Model model, @PathVariable("id") Long id) throws Exception {
		model.addAttribute("entity", service.read(id));
		return "trainingForm-info";
	}

	@RequestMapping(path = "/delete/{id}")
	public String delete(Model model, @PathVariable("id") Long id) throws Exception {
		service.delete(id);
		return "redirect:/trainingForm";
	}

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public String createOrUpdate(TrainingForm entity) throws Exception {
		service.create(entity);
		return "redirect:/trainingForm";
	}
}
