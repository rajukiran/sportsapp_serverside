package com.sports.core.domain;


import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;

@Entity
@Table(name="family_details")
public class FamilyDetails extends BaseDO {
	
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*@EmbeddedId
	private RegistrationId regId;
	
	
	public RegistrationId getRegId() {
		return regId;
	}
public void setRegId(RegistrationId regId) {
		this.regId = regId;
	}*/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private int family_id;
	
	public int getFamily_id() {
		return family_id;
	}
	public void setFamily_id(int family_id) {
		this.family_id = family_id;
	}
	
	private String child_desc;
	
	
	public String getChild_desc() {
		return child_desc;
	}
	public void setChild_desc(String child_desc) {
		this.child_desc = child_desc;
	}
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="child_id")
	
	private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="parent_id")
	private User user1;
	
	public User getUser1() {
		return user1;
	}
	public void setUser1(User user1) {
		this.user1 = user1;
	}
	@Override
	public String toString() {
		return "FamilyDetails [id=" + family_id + ", ChildDesc="+child_desc+", getCreatedBy()=" + getCreatedBy()
				+ ", getCreatedDate()=" + getCreatedDate()
				+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedDate()="
				+ getUpdatedDate() +  "]";
	
	}
}
