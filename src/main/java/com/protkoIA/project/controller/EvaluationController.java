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

import com.protkoIA.project.entity.Evaluation;
import com.protkoIA.project.entity.Session;
import com.protkoIA.project.service.EvaluationService;
import com.protkoIA.project.service.SessionService;
import com.protkoIA.project.service.StudentService;
import com.protkoIA.project.service.SubjectService;

@Controller
@RequestMapping("/evaluation")
public class EvaluationController {

	@Autowired
	EvaluationService service;

	@Autowired
	SessionService serviceSession;

	@Autowired
	SubjectService serviceSubject;

	@Autowired
	StudentService serviceStudent;

	@GetMapping
	String getCategory(Model model) {
		List<Evaluation> list = service.repository.findAll();
		model.addAttribute("list", list);
		return "evaluation-list";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String edit(Model model, @PathVariable(name = "id", required = false) Long id) {
		if (id != null) {
			Evaluation entity = service.read(id);
			model.addAttribute("entity", entity);
		} else {
			model.addAttribute("entity", new Evaluation());
		}
		model.addAttribute("sessions", serviceSession.repository.findAll());
		model.addAttribute("students", serviceStudent.repository.findAll());
		model.addAttribute("subjects", serviceSubject.repository.findAll());
		return "evaluation-add-edit";
	}

	@RequestMapping("/fromSession/edit/{id}")
	public String editFromSession(Model model, @PathVariable(name = "id", required = false) Long id) {
		model.addAttribute("entity", new Evaluation());
		Session session = serviceSession.read(id);
		model.addAttribute("sessions", session);
		model.addAttribute("students", session.getGroup().getStudents());
		model.addAttribute("subjects", session.getSubjects());
		return "evaluation-add-edit";
	}
	@RequestMapping(path = "/get/{id}")
	public String get(Model model, @PathVariable("id") Long id) throws Exception {
		model.addAttribute("entity", service.read(id));
		return "evaluation-info";
	}

	@RequestMapping(path = "/delete/{id}")
	public String delete(Model model, @PathVariable("id") Long id) throws Exception {
		service.delete(id);
		return "redirect:/evaluation";
	}

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public String createOrUpdate(Evaluation entity, 
			@Param("session_id") Long session_id, 
			@Param("student_id") Long student_id, 
			@Param("subject_id") Long subject_id) throws Exception {
		
		entity.setSession(serviceSession.read(session_id));
		entity.setStudent(serviceStudent.read(student_id));
		entity.setSubject(serviceSubject.read(subject_id));
		service.create(entity);
		return "redirect:/evaluation";
	}
}
