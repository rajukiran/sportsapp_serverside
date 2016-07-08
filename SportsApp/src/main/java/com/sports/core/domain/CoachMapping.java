package com.sports.core.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="coach_mapping")
public class CoachMapping extends BaseDO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int mapping_id;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="coach_id")
	private User coach;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="student_id")
	private User student;
	public int getMapping_id() {
		return mapping_id;
	}
	public void setMapping_id(int mapping_id) {
		this.mapping_id = mapping_id;
	}
	public User getCoach() {
		return coach;
	}
	public void setCoach(User coach) {
		this.coach = coach;
	}
	public User getStudent() {
		return student;
	}
	public void setStudent(User student) {
		this.student = student;
	}
	

}
