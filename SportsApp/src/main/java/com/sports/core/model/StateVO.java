package com.sports.core.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;

@Entity
public class StateVO extends BaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
 private int stateId;
 private String stateName;
 private String stateDesc;
 private int countryId;
 private String countryName;
 private String countryDesc;

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
public static long getSerialversionuid() {
	return serialVersionUID;
}



/*private int continent_id;
private String continent_name;
private String continent_desc;

public int getContinent_id() {
	return continent_id;
}
public void setContinent_id(int continent_id) {
	this.continent_id = continent_id;
}
public String getContinent_name() {
	return continent_name;
}
public void setContinent_name(String continent_name) {
	this.continent_name = continent_name;
}
public String getContinent_desc() {
	return continent_desc;
}
public void setContinent_desc(String continent_desc) {
	this.continent_desc = continent_desc;
}*/
/*@OneToOne
@JoinColumn(name="country_id")
private Country country;
public Country getCountry() {
	return country;
}
public void setCountry(Country country) {
	this.country = country;
}*/
@Override
public String toString() {
	return "State [id=" + stateId + ", stateName=" + stateName + ", stateDesc="
			+ stateDesc + ", getCreatedDate()=" + getCreatedDate()
			+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedDate()="
			+ getUpdatedDate() + ", getErrorDO()="
			+ getErrorVO() + "]";

}
 
}
