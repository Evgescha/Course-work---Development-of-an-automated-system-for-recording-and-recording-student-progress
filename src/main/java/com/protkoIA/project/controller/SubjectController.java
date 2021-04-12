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
import com.protkoIA.project.entity.Subject;
import com.protkoIA.project.service.SessionService;
import com.protkoIA.project.service.SubjectService;

@Controller
@RequestMapping("/subject")
public class SubjectController {

	@Autowired
	SubjectService service;

	@Autowired
	SessionService serviceSession;

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

	@RequestMapping("/fromSession/edit/{id}")
	public String editFromSession(Model model, @PathVariable(name = "id", required = false) Long id) {
		model.addAttribute("entity", new Subject());
		return "subject-add-edit";
	}

	@RequestMapping(path = "/get/{id}")
	public String get(Model model, @PathVariable("id") Long id) throws Exception {
		model.addAttribute("entity", service.read(id));
		return "subject-info";
	}

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public String createOrUpdate(Subject entity) throws Exception {
		service.create(entity);
		return "redirect:/subject";
	}

	@RequestMapping(path = { "/editSes", "/editSes/{id}" })
	public String editSes(Model model, @PathVariable(name = "id", required = false) Long id) {
		if (id != null) {
			Subject entity = service.read(id);
			model.addAttribute("entity", entity);
		} else {
			model.addAttribute("entity", new Subject());
		}
		model.addAttribute("list", service.repository.findAll());
		model.addAttribute("sessionId", id);
		return "subjectSession-add-edit";
	}

	@RequestMapping(path = "/deleteSes/{id}_{sessionId}")
	public String delete(Model model, @PathVariable("id") Long id, @PathVariable("sessionId") Long sessionId)
			throws Exception {
		Session session = serviceSession.read(sessionId);
		Subject subject = service.read(id);
		subject.getSessions().remove(session);
		service.update(subject);
		return "redirect:/session/getSubject/" + sessionId;
	}

	@RequestMapping(path = "/createSes", method = RequestMethod.POST)
	public String createOrUpdateSes(@Param("subjectId") Long subjectId, @Param("sessionId") Long sessionId)
			throws Exception {
		Session session = serviceSession.read(sessionId);
		Subject subject = service.read(subjectId);
		subject.getSessions().add(session);
		service.update(subject);
		System.out.println("К сессии с ид: " + sessionId);
		System.out.println("пытаемся добавить предмет с ид: " + subjectId);
		return "redirect:/session/getSubject/" + sessionId;
	}

}
