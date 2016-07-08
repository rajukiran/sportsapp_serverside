package com.sports.core.domain;



import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "organization")
public class Organization extends BaseDO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int org_id;
	private String org_name;
	private String org_desc;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="org_owner")
	private User org_owner;
	
	
	public User getOrg_owner() {
		return org_owner;
	}
	public void setOrg_owner(User org_owner) {
		this.org_owner = org_owner;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getOrg_id() {
		return org_id;
	}
	public void setOrg_id(int org_id) {
		this.org_id = org_id;
	}
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	public String getOrg_desc() {
		return org_desc;
	}
	public void setOrg_desc(String org_desc) {
		this.org_desc = org_desc;
	}
	@Override
	public String toString() {
		return "Organization [id=" + org_id + ", organizationName=" + org_name + ", OrganizationDesc="+org_desc+", getCreatedBy()=" + getCreatedBy()
				+ ", getCreatedDate()=" + getCreatedDate()
				+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedDate()="
				+ getUpdatedDate() +  "]";
	
	}
	
}
