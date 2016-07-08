package com.sports.core.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.swing.text.View;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "user_game_mapping")
public class UserGameMappingVO extends BaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private long id;
	private byte[] profilePic;
	
	

	@Column(name = "first_name" )
	private String firstName;
	
	@Column(name = "last_name" )
	private String lastName;
	
	@Column(name = "date_of_birth" )
	private Date dateOfBirth;
	private String email;
	private String password;
	private String phoneNum;
	private String childDesc;
	private long parentId;
	
	public String getChildDesc() {
		return childDesc;
	}
	public void setChildDesc(String childDesc) {
		this.childDesc = childDesc;
	}
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
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
	
	
	
	@Column(name = "gender" )
	private String gender;
	
	
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


	private int mapping_id;
	private long userId;
	private int gameId;
	private int levelId;
	
    //private User user;
	
    //private Games games;
    
 public int getMapping_id() {
		return mapping_id;
	}

	public void setMapping_id(int mapping_id) {
		this.mapping_id = mapping_id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
    public int[] secGameIds;
    
	public int[] getSecGameIds() {
		return secGameIds;
	}
	public void setSecGameIds(int[] secGameIds) {
		this.secGameIds = secGameIds;
	}
	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	
	// additional fields
    //private boolean activated;
    private String priorityFlag;
    private int yearsOfExp;
    
   
    /*private SkillLevel skilllevel;
    @Id
    @GeneratedValue
    @Column(name = "mapping_id")
	public int getMapping_id() {
		return mapping_id;
	}

	public void setMapping_id(int mapping_id) {
		this.mapping_id = mapping_id;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "user_id")  
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "game_id")  
	public Games getGames() {
		return games;
	}

	public void setGames(Games games) {
		this.games = games;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "level_id") 
	public SkillLevel getSkilllevel() {
		return skilllevel;
	}

	public void setSkilllevel(SkillLevel skilllevel) {
		this.skilllevel = skilllevel;
	}*/
 
	public String getPriorityFlag() {
		return priorityFlag;
	}

	public void setPriorityFlag(String priorityFlag) {
		this.priorityFlag = priorityFlag;
	}

	public int getYearsOfExp() {
		return yearsOfExp;
	}

	public void setYearsOfExp(int yearsOfExp) {
		this.yearsOfExp = yearsOfExp;
	}

	@Override
	public String toString() {
		return "UserGameMapping [id=" + mapping_id + ", user=" + userId + ", games="
				+ gameId + ", skillLevel=" + levelId + ", priorityflag=" + priorityFlag + ", years of exp=" +yearsOfExp + ", getCreatedBy()=" + getCreatedBy()
				+ ", getCreatedDate()=" + getCreatedDate()
				+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedDate()="
				+ getUpdatedDate() + ", getErrorDO()="
				+ getErrorVO() + "]";
	
	}
	
}
