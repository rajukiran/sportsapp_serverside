package com.sports.core.domain;



import java.util.HashSet;
import java.util.List;
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
@Table(name = "games")
public class Games extends BaseDO{
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int game_id;
 private String game_name;
 private String game_desc;
 //private Set<UserGameMapping> userGameMapping = new HashSet<UserGameMapping>();
 
 @Id
@GeneratedValue(strategy = GenerationType.AUTO)
public int getGame_id() {
	return game_id;
}
public void setGame_id(int game_id) {
	this.game_id = game_id;
}
public String getGame_name() {
	return game_name;
}
public void setGame_name(String game_name) {
	this.game_name = game_name;
}
public String getGame_desc() {
	return game_desc;
}
public void setGame_desc(String game_desc) {
	this.game_desc = game_desc;
}
public Games(){
	
}
public Games(String name, String desc) {
	this.game_name = name;
	this.game_desc = desc;
	
}

/*@OneToMany(fetch = FetchType.EAGER,mappedBy = "games")
public Set<UserGameMapping> getUserGameMapping() {
    return userGameMapping;
}

public void setUserGameMapping(Set<UserGameMapping> groups) {
    this.userGameMapping = groups;
}*/
 
/*public void addUserGameMapping(UserGameMapping userGroup) {
    this.userGameMapping.add(userGroup);
}*/
/*public Games(String name, String desc, Set<Employee> employee) {
	this.game_name = name;
	this.game_desc = desc;
	this.employee = employee;
}*/


/*private Set<Employee> employee = new HashSet<Employee>(0);
@ManyToMany(fetch = FetchType.LAZY, mappedBy = "games")
public Set<Employee> getEmployee() {
	return employee;
}
public void setEmployee(Set<Employee> employee) {
	this.employee = employee;
}*/

@Override
public String toString() {
	return "Games [id=" + game_id + ", GameName=" + game_name + ", GameDesc="+game_desc+", getCreatedBy()=" + getCreatedBy()
			+ ", getCreatedDate()=" + getCreatedDate()
			+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedDate()="
			+ getUpdatedDate() +  "]";

}
	
}
