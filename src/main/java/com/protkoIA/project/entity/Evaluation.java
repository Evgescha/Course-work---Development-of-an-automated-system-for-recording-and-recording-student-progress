package com.protkoIA.project.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Evaluation extends AbstractEntity {
	private int evaluation;

	@ManyToOne
	@JoinColumn(name = "session_id")
	private Session session;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	@ManyToOne
	@JoinColumn(name = "subject_id")
	private Subject subject;

}
