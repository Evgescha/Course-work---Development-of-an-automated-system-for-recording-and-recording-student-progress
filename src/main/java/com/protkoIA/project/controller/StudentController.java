package com.protkoIA.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.protkoIA.project.entity.Student;
import com.protkoIA.project.service.GroupService;
import com.protkoIA.project.service.StudentService;
import com.protkoIA.project.service.TrainingFormService;
import com.protkoIA.project.service.TrainingTypeService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService service;
	
	@Autowired
	TrainingFormService serviceTrainingForm;

	@Autowired
	TrainingTypeService serviceTrainingType;
	
	@Autowired
	GroupService serviceGroup;

	@GetMapping
	String getCategory(Model model) {
		List<Student> list = service.repository.findAll();
		model.addAttribute("list", list);
		return "student-list";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String edit(Model model, @PathVariable(name = "id", required = false) Long id) {
		if (id != null) {
			Student entity = service.read(id);
			model.addAttribute("entity", entity);
		} else {
			model.addAttribute("entity", new Student());
		}
		model.addAttribute("forms",serviceTrainingForm.repository.findAll());
		model.addAttribute("types",serviceTrainingType.repository.findAll());
		model.addAttribute("groups",serviceGroup.repository.findAll());
		return "student-add-edit";
	}

	@RequestMapping(path = "/get/{id}")
	public String get(Model model, @PathVariable("id") Long id) throws Exception {
		model.addAttribute("entity", service.read(id));
		return "student-info";
	}

	@RequestMapping(path = "/delete/{id}")
	public String delete(Model model, @PathVariable("id") Long id) throws Exception {
		service.delete(id);
		return "redirect:/subject";
	}

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public String createOrUpdate(Student entity, @PathVariable("training_form_id") Long training_form_id, 
												 @PathVariable("training_type_id") Long training_type_id, 
												 @PathVariable("group_id") Long group_id) throws Exception {
		entity.setGroup(serviceGroup.read(group_id));
		entity.setTrainingForm(serviceTrainingForm.read(training_form_id));
		entity.setTrainingType(serviceTrainingType.read(training_type_id));
		service.create(entity);
		return "redirect:/subject";
	}
}
