package com.sports.core.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="history")
public class HistoryVO extends BaseVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int historyId;
	private int matchId;
	private int teamId;
	private int userId;
	private int schedId;
	/*@OneToOne
	@JoinColumn(name="match_id")
	private Match match;
	@OneToOne
	@JoinColumn(name="team_id")
	private Team team;
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	@OneToOne
	@JoinColumn(name="sched_id")
	private Schedule schedule;*/
	public int getHistoryId() {
		return historyId;
	}


	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}


	public int getMatchId() {
		return matchId;
	}


	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}


	public int getTeamId() {
		return teamId;
	}


	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getSchedId() {
		return schedId;
	}


	public void setSchedId(int schedId) {
		this.schedId = schedId;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	@Override
	public String toString() {
		return "History [id=" + historyId + ", match=" + matchId + ", team="
				+ teamId + ", user=" + userId + ", schedule=" + schedId + ", getCreatedBy()=" + getCreatedBy()
				+ ", getCreatedDate()=" + getCreatedDate()
				+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedDate()="
				+ getUpdatedDate() + ", getErrorDO()="
				+ getErrorVO() + "]";
	
	}


	
	
}
