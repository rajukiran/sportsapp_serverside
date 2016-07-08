package com.sports.core.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Transient;

public class BaseVO implements Serializable {

	
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
	@Transient
	private ErrorVO errorVO;

	public ErrorVO getErrorVO() {
		return errorVO;
	}

	public void setErrorVO(ErrorVO errorVO) {
		this.errorVO = errorVO;
	}
	private boolean sessionValid;
	private String errCode;
	private String errDesc;
	private String errType;
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrDesc() {
		return errDesc;
	}
	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
	}
	public String getErrType() {
		return errType;
	}
	public void setErrType(String errType) {
		this.errType = errType;
	}
	
	public boolean isSessionValid() {
		return sessionValid;
	}
	public void setSessionValid(boolean sessionValid) {
		this.sessionValid = sessionValid;
	}
	
}
