package com.sports.core.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Entity
public class ScheduleVO extends BaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int schedId;
	/*@OneToOne
	@JoinColumn(name="game_id")
	private Games games;
	@OneToOne
	@JoinColumn(name="team_id")
	private Team team;*/
	private String schedDesc;
	private Date startDate;
	private Date endDate;
	private String startTime;
	private String endTime;
	private String meridian;
	private int teamId;
	private String teamName;
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}


	private int gameId;
	private String gameName;
	private int continentId;
	private String continentName;
	private int countryId;
	private String countryName;
	private int stateId;
	private String stateName;
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public String getContinentName() {
		return continentName;
	}
	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public int getSchedId() {
		return schedId;
	}
	public void setSchedId(int schedId) {
		this.schedId = schedId;
	}
	public String getSchedDesc() {
		return schedDesc;
	}
	public void setSchedDesc(String schedDesc) {
		this.schedDesc = schedDesc;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
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
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	/*@OneToOne
	@JoinColumn(name="continent_id")
	private Continent continent;
	@OneToOne
	@JoinColumn(name="country_id")
	private Country country;
	@OneToOne
	@JoinColumn(name="state_id")
	private State state;*/
	private String cityName;
	
	
	public String getMeridian() {
		return meridian;
	}
	public void setMeridian(String meridian) {
		this.meridian = meridian;
	}
	
	
	@Override
	public String toString() {
		return "Schedule [id=" + schedId + ", games=" + gameId + ", team="
				+ teamId + ", ScheduleDesc=" + schedDesc + ", startdate=" + startDate + ", EndDate="+endDate+",startTime="+startTime+",endTime="+endTime+"meridian="+meridian+", getCreatedBy()=" + getCreatedBy()
				+ ", getCreatedDate()=" + getCreatedDate()
				+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedDate()="
				+ getUpdatedDate() + ", getErrorDO()="
				+ getErrorVO() + "]";
	
	}
}
