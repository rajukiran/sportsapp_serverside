package com.sports.core.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "state")
public class State extends BaseDO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
 private int state_id;
 private String state_name;
 private String state_desc;

public int getState_id() {
	return state_id;
}
public void setState_id(int state_id) {
	this.state_id = state_id;
}
public String getState_name() {
	return state_name;
}
public void setState_name(String state_name) {
	this.state_name = state_name;
}
public String getState_desc() {
	return state_desc;
}
public void setState_desc(String state_desc) {
	this.state_desc = state_desc;
}

@OneToOne
@JoinColumn(name="country_id")
private Country country;
public Country getCountry() {
	return country;
}
public void setCountry(Country country) {
	this.country = country;
}
@Override
public String toString() {
	return "State [id=" + state_id + ", stateName=" + state_name + ", stateDesc="
			+ state_desc + ", getCreatedDate()=" + getCreatedDate()
			+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedDate()="
			+ getUpdatedDate() +  "]";

}
 
}
