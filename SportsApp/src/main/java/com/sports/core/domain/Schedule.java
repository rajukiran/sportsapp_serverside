package com.sports.core.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "schedule")
public class Schedule extends BaseDO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int sched_id;
	@OneToOne
	@JoinColumn(name="game_id")
	private Games games;
	@OneToOne
	@JoinColumn(name="team_id")
	private Team team;
	private String sched_desc;
	private Date start_date;
	private Date end_date;
	private String start_time;
	private String end_time;
	private String meridian;
	@OneToOne
	@JoinColumn(name="continent_id")
	private Continent continent;
	@OneToOne
	@JoinColumn(name="country_id")
	private Country country;
	@OneToOne
	@JoinColumn(name="state_id")
	private State state;
	private String city_name;
	
	public int getSched_id() {
		return sched_id;
	}
	public void setSched_id(int sched_id) {
		this.sched_id = sched_id;
	}
	public Games getGames() {
		return games;
	}
	public void setGames(Games games) {
		this.games = games;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public String getSched_desc() {
		return sched_desc;
	}
	public void setSched_desc(String sched_desc) {
		this.sched_desc = sched_desc;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getMeridian() {
		return meridian;
	}
	public void setMeridian(String meridian) {
		this.meridian = meridian;
	}
	public Continent getContinent() {
		return continent;
	}
	public void setContinent(Continent continent) {
		this.continent = continent;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	
	@Override
	public String toString() {
		return "Schedule [id=" + sched_id + ", games=" + games + ", team="
				+ team + ", ScheduleDesc=" + sched_desc + ", startdate=" + start_date + ", EndDate="+end_date+",startTime="+start_time+",endTime="+end_time+"meridian="+meridian+", getCreatedBy()=" + getCreatedBy()
				+ ", getCreatedDate()=" + getCreatedDate()
				+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedDate()="
				+ getUpdatedDate() +  "]";
	
	}
}
