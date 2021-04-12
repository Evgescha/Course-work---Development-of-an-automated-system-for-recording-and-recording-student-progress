package com.protkoIA.project.controller;

import java.util.ArrayList;
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
import com.protkoIA.project.entity.Subject;
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

	@RequestMapping("/fromGroup/edit/{id}")
	public String editFromGroup(Model model, @PathVariable(name = "id", required = false) Long id) {
		model.addAttribute("entity", new Session());
		model.addAttribute("groups", serviceGroup.read(id));
		return "session-add-edit";
	}
	@RequestMapping(path = "/getSubject/{id}")
	public String getSubject(Model model, @PathVariable("id") Long id) throws Exception {
		Session session = service.read(id);
		model.addAttribute("from", true);
		model.addAttribute("entity", session);
		
		
		ArrayList<SessionSubject> list = new ArrayList<SessionSubject>();
		for(Subject sbj:session.getSubjects()){
			int countCurrentSubject=0;
			int sumCurrentSubject=0;
			for(Evaluation ev:session.getEvaluations()) {
				if(ev.getSubject().getId()==sbj.getId()) {
					countCurrentSubject++;
					sumCurrentSubject+=ev.getEvaluation();
				}
			}
			list.add(new SessionSubject(sbj, countCurrentSubject, sumCurrentSubject));
		}
		
		model.addAttribute("list", list);
		return "subjectSession-list";
	}

	@RequestMapping(path = "/getEvaluation/{id}")
	public String getEvaluation(Model model, @PathVariable("id") Long id) throws Exception {
		Session group = service.read(id);
		model.addAttribute("from", true);
		model.addAttribute("entity", group);
		model.addAttribute("list", group.getEvaluations());
		return "evaluation-list";
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
class SessionSubject{
	Long id;
	String name;
	int countCurrentSubject=0;
	int sumCurrentSubject=0;
	SessionSubject(Subject subject, int countCurrentSubject, int sumCurrentSubject){
		id=subject.getId();
		name=subject.getName();
		this.countCurrentSubject=countCurrentSubject;
		this.sumCurrentSubject=sumCurrentSubject;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCountCurrentSubject() {
		return countCurrentSubject;
	}
	public void setCountCurrentSubject(int countCurrentSubject) {
		this.countCurrentSubject = countCurrentSubject;
	}
	public int getSumCurrentSubject() {
		return sumCurrentSubject;
	}
	public void setSumCurrentSubject(int sumCurrentSubject) {
		this.sumCurrentSubject = sumCurrentSubject;
	}
	
}