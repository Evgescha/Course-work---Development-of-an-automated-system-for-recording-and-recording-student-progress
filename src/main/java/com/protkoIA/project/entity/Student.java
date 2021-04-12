package com.protkoIA.project.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Student extends AbstractEntity {

	private String fio;
	private Date dateBorn;
	private String phone;
	private String adres;
	private long cardNumber;

	@ManyToOne
	@JoinColumn(name = "training_form_id")
	private TrainingForm trainingForm;

	@ManyToOne
	@JoinColumn(name = "training_type_id")
	private TrainingType trainingType;

	@ManyToOne
	@JoinColumn(name = "group_id")
	private Group group;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
	private List<Evaluation> evaluations = new ArrayList<Evaluation>();

}