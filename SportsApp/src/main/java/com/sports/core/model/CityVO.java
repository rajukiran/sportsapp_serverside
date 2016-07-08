package com.sports.core.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class CityVO extends BaseVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cityId;
	private String cityName;
	private String cityDesc;
	/*@OneToOne
	@JoinColumn(name="state_id")
	private State state;*/
	
	/*public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}*/
	private int stateId;
	 private String stateName;
	 private String stateDesc;

	
	private int countryId;
	private String countryName;
	private String countryDesc;

	
	private int continentId;
	private String continentName;
	private String continentDesc;

	
	public int getCityId() {
		return cityId;
	}


	public void setCityId(int cityId) {
		this.cityId = cityId;
	}


	public String getCityName() {
		return cityName;
	}


	public void setCityName(String cityName) {
		this.cityName = cityName;
	}


	public String getCityDesc() {
		return cityDesc;
	}


	public void setCityDesc(String cityDesc) {
		this.cityDesc = cityDesc;
	}


	public int getStateId() {
		return stateId;
	}


	public void setStateId(int stateId) {
		this.stateId = stateId;
	}


	public String getStateName() {
		return stateName;
	}


	public void setStateName(String stateName) {
		this.stateName = stateName;
	}


	public String getStateDesc() {
		return stateDesc;
	}


	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
	}


	public int getCountryId() {
		return countryId;
	}


	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}


	public String getCountryName() {
		return countryName;
	}


	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}


	public String getCountryDesc() {
		return countryDesc;
	}


	public void setCountryDesc(String countryDesc) {
		this.countryDesc = countryDesc;
	}


	public int getContinentId() {
		return continentId;
	}


	public void setContinentId(int continentId) {
		this.continentId = continentId;
	}


	public String getContinentName() {
		return continentName;
	}


	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}


	public String getContinentDesc() {
		return continentDesc;
	}


	public void setContinentDesc(String continentDesc) {
		this.continentDesc = continentDesc;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "City [id=" + cityId + ", cityName=" + cityName + ", CityDesc="+cityDesc+", getCreatedBy()=" + getCreatedBy()
				+ ", getCreatedDate()=" + getCreatedDate()
				+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedDate()="
				+ getUpdatedDate() + ", getErrorDO()="
				+ getErrorVO() + "]";
	
	}
}
