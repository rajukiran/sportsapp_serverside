package com.sports.core.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class City extends BaseDO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int city_id;
	private String city_name;
	private String city_desc;
	@OneToOne
	@JoinColumn(name="state_id")
	private State state;
	public int getCity_id() {
		return city_id;
	}
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getCity_desc() {
		return city_desc;
	}
	public void setCity_desc(String city_desc) {
		this.city_desc = city_desc;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "City [id=" + city_id + ", cityName=" + city_name + ", CityDesc="+city_desc+", getCreatedBy()=" + getCreatedBy()
				+ ", getCreatedDate()=" + getCreatedDate()
				+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedDate()="
				+ getUpdatedDate()  + "]";
	
	}
}
