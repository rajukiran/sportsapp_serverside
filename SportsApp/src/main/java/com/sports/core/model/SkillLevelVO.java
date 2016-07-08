package com.sports.core.model;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="skill_level")
public class SkillLevelVO extends BaseVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int levelId;
	private String levelName;
	private String levelDesc;
	public int getLevelId() {
		return levelId;
	}


	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}


	public String getLevelName() {
		return levelName;
	}


	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}


	public String getLevelDesc() {
		return levelDesc;
	}


	public void setLevelDesc(String levelDesc) {
		this.levelDesc = levelDesc;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/*@OneToMany(mappedBy = "skilllevel")
	private Set<UserGameMapping> userGameMapping = new HashSet<UserGameMapping>();*/
	public SkillLevelVO(){
		
	}
	/*public SkillLevel(String levelName, String levelDesc) {		
		// TODO Auto-generated constructor stub
		this.levelName = levelName;
		this.levelDesc = levelDesc;
	}*/
	
	
    /*public Set<UserGameMapping> getUserGroups() {
        return userGameMapping;
    }
 
    public void setUserGroups(Set<UserGameMapping> groups) {
        this.userGameMapping = groups;
    }
     
    public void addUserGroup(UserGameMapping userGroup) {
        this.userGameMapping.add(userGroup);
    } */
	/*@ManyToMany(fetch = FetchType.LAZY, mappedBy = "skilllevel")
	private Set<Employee> employee = new HashSet<Employee>(0);
	
	public Set<Employee> getEmployee() {
		return employee;
	}
	public void setEmployee(Set<Employee> employee) {
		this.employee = employee;
	}*/
	@Override
	public String toString() {
		return "SkillLevel [id=" + levelId + ", LevelName=" + levelName + ", levelDesc="
				+ levelDesc + ", getCreatedBy()=" + getCreatedBy()
				+ ", getCreatedDate()=" + getCreatedDate()
				+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedDate()="
				+ getUpdatedDate() + ", getErrorDO()="
				+ getErrorVO() + "]";
	
	}


	
}
