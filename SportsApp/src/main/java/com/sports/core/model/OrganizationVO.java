package com.sports.core.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class OrganizationVO extends BaseVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orgId;
	private String orgName;
	private String orgDesc;
	private long orgOwner;
	private long userId;
	private String orgOwnerFirstName;
	private String orgOwnerLastName;
	public String getOrgOwnerFirstName() {
		return orgOwnerFirstName;
	}


	public void setOrgOwnerFirstName(String orgOwnerFirstName) {
		this.orgOwnerFirstName = orgOwnerFirstName;
	}


	public String getOrgOwnerLastName() {
		return orgOwnerLastName;
	}


	public void setOrgOwnerLastName(String orgOwnerLastName) {
		this.orgOwnerLastName = orgOwnerLastName;
	}


	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}


	public Long getOrgOwner() {
		return orgOwner;
	}


	public void setOrgOwner(Long orgOwner) {
		this.orgOwner = orgOwner;
	}


	public int getOrgId() {
		return orgId;
	}


	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}


	public String getOrgName() {
		return orgName;
	}


	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


	public String getOrgDesc() {
		return orgDesc;
	}


	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "Organization [id=" + orgId + ", organizationName=" + orgName + ", OrganizationDesc="+orgDesc+", getCreatedBy()=" + getCreatedBy()
				+ ", getCreatedDate()=" + getCreatedDate()
				+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedDate()="
				+ getUpdatedDate() + ", getErrorDO()="
				+ getErrorVO() + "]";
	
	}
	
}
