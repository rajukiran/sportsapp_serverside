package com.sports.core.model;



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

@Entity
public class GamesVO extends BaseVO{
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private int gameId;
 private String gameName;
 private String gameDesc;
 //private Set<UserGameMapping> userGameMapping = new HashSet<UserGameMapping>();
 
 
 public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getGameDesc() {
		return gameDesc;
	}

	public void setGameDesc(String gameDesc) {
		this.gameDesc = gameDesc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
	return "Games [id=" + gameId + ", GameName=" + gameName + ", GameDesc="+gameDesc+", getCreatedBy()=" + getCreatedBy()
			+ ", getCreatedDate()=" + getCreatedDate()
			+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedDate()="
			+ getUpdatedDate() + ", getErrorDO()="
			+ getErrorVO() + "]";

}


	
}
