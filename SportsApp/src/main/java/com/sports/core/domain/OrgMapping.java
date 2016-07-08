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
@Table(name="org_mapping")
public class OrgMapping extends BaseDO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int org_mapping_id;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="org_id")
	private Organization org;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User user;
	public int getOrg_mapping_id() {
		return org_mapping_id;
	}
	public void setOrg_mapping_id(int org_mapping_id) {
		this.org_mapping_id = org_mapping_id;
	}
	public Organization getOrg() {
		return org;
	}
	public void setOrg(Organization org) {
		this.org = org;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
