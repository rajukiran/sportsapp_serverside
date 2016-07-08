package com.sports.core.domain;



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
public class SkillLevel extends BaseDO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int level_id;
	private String level_name;
	private String level_desc;
	/*@OneToMany(mappedBy = "skilllevel")
	private Set<UserGameMapping> userGameMapping = new HashSet<UserGameMapping>();*/
	public SkillLevel(){
		
	}
	public SkillLevel(String level_name, String level_desc) {		
		// TODO Auto-generated constructor stub
		this.level_name = level_name;
		this.level_desc = level_desc;
	}
	public int getLevel_id() {
		return level_id;
	}
	public void setLevel_id(int level_id) {
		this.level_id = level_id;
	}
	public String getLevel_name() {
		return level_name;
	}
	public void setLevel_name(String level_name) {
		this.level_name = level_name;
	}
	public String getLevel_desc() {
		return level_desc;
	}
	public void setLevel_desc(String level_desc) {
		this.level_desc = level_desc;
	}
	
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
		return "SkillLevel [id=" + level_id + ", LevelName=" + level_name + ", levelDesc="
				+ level_desc + ", getCreatedBy()=" + getCreatedBy()
				+ ", getCreatedDate()=" + getCreatedDate()
				+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedDate()="
				+ getUpdatedDate() +  "]";
	
	}
}
