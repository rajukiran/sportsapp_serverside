package com.sports.core.domain;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="matches")
public class Match extends BaseDO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int match_id;
	private String match_name;
	private String match_desc;
	@OneToOne
	@JoinColumn(name="team_id")
	private Team team;
	@OneToOne
	@JoinColumn(name="sched_id")
	private Schedule schedule;
	private String status;
	
	public int getMatch_id() {
		return match_id;
	}
	public void setMatch_id(int match_id) {
		this.match_id = match_id;
	}
	public String getMatch_name() {
		return match_name;
	}
	public void setMatch_name(String match_name) {
		this.match_name = match_name;
	}
	public String getMatch_desc() {
		return match_desc;
	}
	public void setMatch_desc(String match_desc) {
		this.match_desc = match_desc;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Matches [id=" + match_id + ", matchName=" + match_name + ", MatchDesc="
				+ match_desc + ", team=" + team + ", schedule=" + schedule + ", getCreatedBy()=" + getCreatedBy()
				+ ", getCreatedDate()=" + getCreatedDate()
				+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedDate()="
				+ getUpdatedDate() +  "]";
	
	}
	
}
