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
@Table(name="team_mapping")
public class TeamMapping extends BaseDO{
    
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int mapping_id;
@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name="team_id")
private Team team;
@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name="user_id")
private User user;
public int getMapping_id() {
	return mapping_id;
}
public void setMapping_id(int mapping_id) {
	this.mapping_id = mapping_id;
}
public Team getTeam() {
	return team;
}
public void setTeam(Team team) {
	this.team = team;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
@Override
public String toString() {
	return "TeamMapping [id=" + mapping_id + ", Team="+team+", getCreatedBy()=" + getCreatedBy()
			+ ", getCreatedDate()=" + getCreatedDate()
			+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedDate()="
			+ getUpdatedDate() +  "]";

}	

}
