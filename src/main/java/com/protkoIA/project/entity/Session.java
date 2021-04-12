package com.protkoIA.project.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Session extends AbstractEntity {

	private String name;
	private String semestr;

	@ManyToOne
	@JoinColumn(name = "group_id")
	private Group group;
	
	@ManyToMany(mappedBy = "sessions", fetch = FetchType.EAGER)
	private Collection<Subject> subjects = new ArrayList<Subject>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "session")
	private Collection<Evaluation> evaluations= new ArrayList<Evaluation>();

	@Override
	public String toString() {
		return name + ", " + semestr + " семестр, " + group;
	}
	
	
}