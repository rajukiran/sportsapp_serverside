package com.sports.core.domain;

import java.io.Serializable;
//import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.sports.core.domain.ErrorDO;
@MappedSuperclass
public class BaseDO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Column(name = "created_by" )
	private String createdBy; 	
	
	
	@Column(name = "created_date" )
	private Date createdDate; 	
	
	
	@Column(name = "updated_by" )
	private String updatedBy; 	
	
	
	@Column(name = "updated_Date" )
	private Date updatedDate;
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	

	

	
	
}
