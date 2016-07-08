package com.sports.core.model;

//import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
//import java.util.Arrays;
//import java.util.HashSet;
import java.util.List;
//import java.util.Set;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.swing.text.View;

import com.fasterxml.jackson.annotation.JsonView;









@Entity
@Table(name = "user")
public class UserVO extends BaseVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 
	 */
	
	private long id;
	
	
	

	@Column(name = "first_name" )
	private String firstName;
	
	@Column(name = "last_name" )
	private String lastName;
	
	@Column(name = "date_of_birth" )
	private Date dateOfBirth;
	public UserVO(){
		
	}
	public UserVO(String firstName,String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	private String email;
	private String password;
	private String phoneNum;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public byte[] getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(byte[] profilePic) {
		this.profilePic = profilePic;
	}

	@Column(name = "gender" )
	private String gender;
	
	@Column(name = "profile_pic" )
	private byte[] profilePic; 	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinTable(name="role_mapping", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="role_id"))
	private com.sports.core.domain.Role role;  
	 
	   
		public com.sports.core.domain.Role getRole() {
		return role;
	}
	public void setRole(com.sports.core.domain.Role role) {
		this.role = role;
	}


	/*@OneToMany(cascade = CascadeType.ALL)
		@JoinTable(name="user_game_mapping", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="game_id"))
		
		private List<Games> games;
		
		
	public List<Games> getGames() {
			return games;
		}

		public void setGames(List<Games> games) {
			this.games = games;
		}*/
	private int roleId;
	private String roleName;
	
	
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
		public static long getSerialversionuid() {
			return serialVersionUID;
		}

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public Date getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	
	/*@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "user")
	private Set<UserGameMapping> userGameMappings = new HashSet<UserGameMapping>();
	 public Set<UserGameMapping> getUserGameMapping() {
	        return userGameMappings;
	    }
	    public void setUserGameMapping(Set<UserGameMapping> groups) {
	        this.userGameMappings = groups;
	    }
	     
	    public void addUserGameMapping(UserGameMapping userGameMapping) {
	        this.userGameMappings.add(userGameMapping);
	    }  
	public void addGames(UserGameMapping games) {
        this.userGameMappings.add(games);
    }
	public void addSkillLevel(UserGameMapping skilllevel) {
        this.userGameMappings.add(skilllevel);
    }*/
	private String date;
	
public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

private int gameId;
private String gameName;
private int levelId;
private String levelName;
private int yearsOfExp;
	private int mappingId;
	private int[] secGameIds;
	private int[] secMappingIds;
	
	public int[] getSecMappingIds() {
		return secMappingIds;
	}
	public void setSecMappingIds(int[] secMappingIds) {
		this.secMappingIds = secMappingIds;
	}

	private String priorityFlag;

	public String getPriorityFlag() {
		return priorityFlag;
	}
	public void setPriorityFlag(String priorityFlag) {
		this.priorityFlag = priorityFlag;
	}
	public int[] getSecGameIds() {
		return secGameIds;
	}
	public void setSecGameIds(int[] secGameIds) {
		this.secGameIds = secGameIds;
	}
	public int getMappingId() {
		return mappingId;
	}
	public void setMappingId(int mappingId) {
		this.mappingId = mappingId;
	}
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
public int getLevelId() {
	return levelId;
}
public void setLevelId(int levelId) {
	this.levelId = levelId;
}
public String getLevelName() {
	return levelName;
}
public void setLevelName(String levelName) {
	this.levelName = levelName;
}
public int getYearsOfExp() {
	return yearsOfExp;
}
public void setYearsOfExp(int yearsOfExp) {
	this.yearsOfExp = yearsOfExp;
}
private int addrId;

public int getAddrId() {
	return addrId;
}
public void setAddrId(int addrId) {
	this.addrId = addrId;
}
private long parentId;
private String parentFirstName;
private String parentLastName;

public long getParentId() {
	return parentId;
}
public void setParentId(long parentId) {
	this.parentId = parentId;
}
public String getParentFirstName() {
	return parentFirstName;
}
public void setParentFirstName(String parentFirstName) {
	this.parentFirstName = parentFirstName;
}
public String getParentLastName() {
	return parentLastName;
}
public void setParentLastName(String parentLastName) {
	this.parentLastName = parentLastName;
}

private String addrType1;
private String addrType2;
private String pincode;
private int continentId;
private int countryId;
private int stateId;
private int cityId;


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
public int getCityId() {
	return cityId;
}
public void setCityId(int cityId) {
	this.cityId = cityId;
}
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
public String getPincode() {
	return pincode;
}
public void setPincode(String pincode) {
	this.pincode = pincode;
}
private int geoLocationId;

public int getGeoLocationId() {
	return geoLocationId;
}
public void setGeoLocationId(int geoLocationId) {
	this.geoLocationId = geoLocationId;
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
		return "User [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", getCreatedBy()=" + getCreatedBy()
				+ ", getCreatedDate()=" + getCreatedDate()
				+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedDate()="
				+ getUpdatedDate() + ", getErrorVO()="
				+ getErrorVO() + "]";
	
	}

	
	


	 	
	
	
	
}
