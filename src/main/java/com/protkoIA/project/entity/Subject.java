package com.protkoIA.project.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Subject extends AbstractEntity {

	private String name;

	@JsonIgnore
    @Fetch(value = FetchMode.SELECT)
    @ManyToMany(cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinTable(name = "subject_session",
        joinColumns = @JoinColumn(name = "subject_id"),
        inverseJoinColumns = @JoinColumn(name = "session_id"),
        uniqueConstraints = @UniqueConstraint(
                name="subjects_sessions",
                columnNames = {"subject_id", "session_id"})
    )
	private Collection<Session> sessions = new ArrayList<Session>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subject")
	private List<Evaluation> evaluations = new ArrayList<Evaluation>();
	
	@Override
	public String toString() {
		return name;
	}
}
