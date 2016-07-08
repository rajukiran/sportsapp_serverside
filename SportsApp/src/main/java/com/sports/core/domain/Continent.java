package com.sports.core.domain;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "continent")
public class Continent extends BaseDO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int continent_id;
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
	}
	@Override
	public String toString() {
		return "Continent [id=" + continent_id + ", continentName=" + continent_name + ", ContinentDesc="+continent_desc+", getCreatedBy()=" + getCreatedBy()
				+ ", getCreatedDate()=" + getCreatedDate()
				+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedDate()="
				+ getUpdatedDate() +  "]";
	
	}
	
}
