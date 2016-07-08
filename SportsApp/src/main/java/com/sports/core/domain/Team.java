package com.sports.core.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
//import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "team")
public class Team extends BaseDO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private int team_id;
private String team_name;
private String team_desc;


private String team_org;
private int player_count;
private int vacancy;
@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name="team_owner")

private User teamOwner;
public User getTeamOwner() {
	return teamOwner;
}
public void setTeamOwner(User teamOwner) {
	this.teamOwner = teamOwner;
}
@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name="team_coach")

private User teamCoach;
public User getTeamCoach() {
	return teamCoach;
}
public void setTeamCoach(User teamCoach) {
	this.teamCoach = teamCoach;
}
@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name="team_captain")

private User teamCaptain;
public User getTeamCaptain() {
	return teamCaptain;
}
public void setTeamCaptain(User teamCaptain) {
	this.teamCaptain = teamCaptain;
}
public int getVacancy() {
	return vacancy;
}
public void setVacancy(int vacancy) {
	this.vacancy = vacancy;
}
public int getTeam_id() {
	return team_id;
}
public void setTeam_id(int team_id) {
	this.team_id = team_id;
}
public String getTeam_name() {
	return team_name;
}
public void setTeam_name(String team_name) {
	this.team_name = team_name;
}
public String getTeam_desc() {
	return team_desc;
}
public void setTeam_desc(String team_desc) {
	this.team_desc = team_desc;
}

public String getTeam_org() {
	return team_org;
}
public void setTeam_org(String team_org) {
	this.team_org = team_org;
}
public int getPlayer_count() {
	return player_count;
}
public void setPlayer_count(int player_count) {
	this.player_count = player_count;
}
@OneToOne
@JoinColumn(name="game_id")
private Games games;

public Games getGames() {
	return games;
}
public void setGames(Games games) {
	this.games = games;
}

@Override
public String toString() {
	return "Team [id=" + team_id + ", teamName=" + team_name + ", teamDesc="
			+ team_desc + ", teamOwner=" + teamOwner + ", teamCaptain=" + teamCaptain + ",TeamCoach=" +teamCoach+ ",TeamOrg=" +team_org+ ", games=" +games+ ", getCreatedBy()=" + getCreatedBy()
			+ ", getCreatedDate()=" + getCreatedDate()
			+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedDate()="
			+ getUpdatedDate() +  "]";

}
	
}
