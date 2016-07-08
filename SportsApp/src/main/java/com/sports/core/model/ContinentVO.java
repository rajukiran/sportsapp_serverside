package com.sports.core.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class ContinentVO extends BaseVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int continentId;
	private String continentName;
	private String continentDesc;
	
	
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
		return "Continent [id=" + continentId + ", continentName=" + continentName + ", ContinentDesc="+continentDesc+", getCreatedBy()=" + getCreatedBy()
				+ ", getCreatedDate()=" + getCreatedDate()
				+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedDate()="
				+ getUpdatedDate() + ", getErrorDO()="
				+ getErrorVO() + "]";
	
	}
	
}
