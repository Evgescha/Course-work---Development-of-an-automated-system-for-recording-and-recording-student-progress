package com.protkoIA.project.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "myGroup")
@Data
public class Group extends AbstractEntity {

	private String name;
	private int yearStart;
	private int yearEnd;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
	private List<Student> students = new ArrayList<Student>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
	private List<Session> sessions = new ArrayList<Session>();

	@Override
	public String toString() {
		return name + " (" + yearStart + " - " + yearEnd + ")";
	}



}
