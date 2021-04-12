package com.protkoIA.project.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class TrainingType extends AbstractEntity {

	private String name;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "trainingType")
	private List<Student> students = new ArrayList<Student>();

	@Override
	public String toString() {
		return name;
	}
}
