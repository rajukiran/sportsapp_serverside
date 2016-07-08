package com.sports.core.model;


import java.sql.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;

@Entity
@Table(name="family_details")
public class FamilyDetailsVO extends BaseVO {
	
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
private int familyId;
	
	public int getFamilyId() {
		return familyId;
	}
	public void setFamilyId(int family_id) {
		this.familyId = family_id;
	}
	
	private String childDesc;
	private String childName;
	private String parentName;
	private String childLastName;
	private String childemail;
	private String childpassword;
	private Date childDateOfBirth;
	private String childGender;
	private String phoneNum;
	
	public String getChildLastName() {
		return childLastName;
	}
	public void setChildLastName(String childLastName) {
		this.childLastName = childLastName;
	}
	public String getChildemail() {
		return childemail;
	}
	public void setChildemail(String childemail) {
		this.childemail = childemail;
	}
	public String getChildpassword() {
		return childpassword;
	}
	public void setChildpassword(String childpassword) {
		this.childpassword = childpassword;
	}
	public Date getChildDateOfBirth() {
		return childDateOfBirth;
	}
	public void setChildDateOfBirth(Date date) {
		this.childDateOfBirth = date;
	}
	public String getChildGender() {
		return childGender;
	}
	public void setChildGender(String childGender) {
		this.childGender = childGender;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getChildDesc() {
		return childDesc;
	}
	public void setChildDesc(String childDesc) {
		this.childDesc = childDesc;
	}
	@Override
	public String toString() {
		return "FamilyDetails [id=" + familyId + ", ChildDesc="+childDesc+", getCreatedBy()=" + getCreatedBy()
				+ ", getCreatedDate()=" + getCreatedDate()
				+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedDate()="
				+ getUpdatedDate() + ", getErrorDO()="
				+ getErrorVO() + "]";
	
	}
}
