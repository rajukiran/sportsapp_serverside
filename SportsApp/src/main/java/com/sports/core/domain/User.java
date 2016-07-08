package com.sports.core.domain;

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
import javax.persistence.UniqueConstraint;
import javax.swing.text.View;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;
import org.springframework.web.cors.CorsUtils;

import com.fasterxml.jackson.annotation.JsonView;










@Entity
@Table(name = "user",uniqueConstraints = {
        
        @UniqueConstraint(columnNames = "email") })
public class User extends BaseDO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private long id;
	
	@Column(name = "first_name" )
	private String firstName;
	
	@Column(name = "last_name" )
	private String lastName;
	
	@Column(name = "password" )
	private String password;
	 @NaturalId (mutable = false)
	    @Column(name = "email", unique = true, nullable = false)
	private String email;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	@Column(name = "date_of_birth" )
	private Date dateOfBirth;
	public User(){
		
	}
	public User(String firstName,String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
	}
	@Column(name = "phone_num" )
	private String phoneNum;
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}


	@Column(name = "gender" )
	private String gender;
	
	@Column(name = "profile_pic" )
	private byte[] profilePic; 	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name="role_mapping", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="role_id"))
	private Role role;  
	 
	   public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
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

		

	public long getId() {
		return id;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
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


	

	/*public short getRole() {
		return role;
	}


	public void setRole(short role) {
		this.role = role;
	}*/


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
	
	

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", getCreatedBy()=" + getCreatedBy()
				+ ", getCreatedDate()=" + getCreatedDate()
				+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedDate()="
				+ getUpdatedDate() +  "]";
	
	}

	
	


	 	
	
	
	
}
