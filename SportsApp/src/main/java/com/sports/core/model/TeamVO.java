package com.sports.core.model;

//import javax.persistence.CascadeType;
//import javax.persistence.CascadeType;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class TeamVO extends BaseVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private int teamId;
private String teamName;
private String teamDesc;

private String teamOrg;
private int playerCount;
private int gameId;
private String gameName;
private long userId;
private String createdBy;
private String updatedBy;
private int vacancy;
private long teamOwner;
private long teamCoach;
private long teamCaptain;
private String teamOwnerName;
private String teamCoachName;
private String teamCaptainName;
private int continentId;
private int countryId;
private int stateId;
private String pincode;
private String addrType1;
private String addrType2;

public String getAddrType1() {
	return addrType1;
}
public void setAddrType1(String addrType1) {
	this.addrType1 = addrType1;
}
public String getAddrType2() {
	return addrType2;
}
public void setAddrType2(String addrType2) {
	this.addrType2 = addrType2;
}
public int getContinentId() {
	return continentId;
}
public void setContinentId(int continentId) {
	this.continentId = continentId;
}
public int getCountryId() {
	return countryId;
}
public void setCountryId(int countryId) {
	this.countryId = countryId;
}
public int getStateId() {
	return stateId;
}
public void setStateId(int stateId) {
	this.stateId = stateId;
}
public String getPincode() {
	return pincode;
}
public void setPincode(String pincode) {
	this.pincode = pincode;
}
public long getTeamOwner() {
	return teamOwner;
}
public void setTeamOwner(long l) {
	this.teamOwner = l;
}
public long getTeamCoach() {
	return teamCoach;
}
public void setTeamCoach(long teamCoach) {
	this.teamCoach = teamCoach;
}
public long getTeamCaptain() {
	return teamCaptain;
}
public void setTeamCaptain(long teamCaptain) {
	this.teamCaptain = teamCaptain;
}
public String getTeamOwnerName() {
	return teamOwnerName;
}
public void setTeamOwnerName(String teamOwnerName) {
	this.teamOwnerName = teamOwnerName;
}
public String getTeamCoachName() {
	return teamCoachName;
}
public void setTeamCoachName(String teamCoachName) {
	this.teamCoachName = teamCoachName;
}
public String getTeamCaptainName() {
	return teamCaptainName;
}
public void setTeamCaptainName(String teamCaptainName) {
	this.teamCaptainName = teamCaptainName;
}
public int getVacancy() {
	return vacancy;
}
public void setVacancy(int vacancy) {
	this.vacancy = vacancy;
}
public String getCreatedBy() {
	return createdBy;
}
public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
}
public String getUpdatedBy() {
	return updatedBy;
}
public void setUpdatedBy(String updatedBy) {
	this.updatedBy = updatedBy;
}
public long getUserId() {
	return userId;
}
public void setUserId(long userId) {
	this.userId = userId;
}
public String getGameName() {
	return gameName;
}
public void setGameName(String gameName) {
	this.gameName = gameName;
}
public int getTeamId() {
	return teamId;
}
public void setTeamId(int teamId) {
	this.teamId = teamId;
}
public String getTeamName() {
	return teamName;
}
public void setTeamName(String teamName) {
	this.teamName = teamName;
}
public String getTeamDesc() {
	return teamDesc;
}
public void setTeamDesc(String teamDesc) {
	this.teamDesc = teamDesc;
}

public String getTeamOrg() {
	return teamOrg;
}
public void setTeamOrg(String teamOrg) {
	this.teamOrg = teamOrg;
}
public int getPlayerCount() {
	return playerCount;
}
public void setPlayerCount(int playerCount) {
	this.playerCount = playerCount;
}
public int getGameId() {
	return gameId;
}
public void setGameId(int gameId) {
	this.gameId = gameId;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}
private float geoLocationLong;
private float geoLocationLatit;



public float getGeoLocationLong() {
	return geoLocationLong;
}
public void setGeoLocationLong(float geoLocationLong) {
	this.geoLocationLong = geoLocationLong;
}
public float getGeoLocationLatit() {
	return geoLocationLatit;
}
public void setGeoLocationLatit(float geoLocationLatit) {
	this.geoLocationLatit = geoLocationLatit;
}
@Override
public String toString() {
	return "Team [id=" + teamId + ", TeamName=" + teamName + ", teamOwner="
			+ teamOwner + ", games =" +gameId+ ", getCreatedBy()=" + getCreatedBy()
			+ ", getCreatedDate()=" + getCreatedDate()
			+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedDate()="
			+ getUpdatedDate() + ", getErrorVO()="
			+ getErrorVO() + "]";

}


	
}
