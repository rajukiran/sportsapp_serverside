package com.sports.core.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.swing.text.View;

import com.fasterxml.jackson.annotation.JsonView;


@Entity
@Table(name = "user_game_mapping")

public class UserGameMapping extends BaseDO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	    

	private int mapping_id;
	
    private User user;
	
    private Games games;
    
 // additional fields
    //private boolean activated;
    private String priority_flag;
    private int years_of_exp;
    
    public String getPriority_flag() {
		return priority_flag;
	}

	public void setPriority_flag(String priority_flag) {
		this.priority_flag = priority_flag;
	}

	public int getYears_of_exp() {
		return years_of_exp;
	}

	public void setYears_of_exp(int years_of_exp) {
		this.years_of_exp = years_of_exp;
	}
    private SkillLevel skilllevel;
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
	}
 
	@Override
	public String toString() {
		return "UserGameMapping [ user=" + user + ", games="
				+ games + ", skillLevel=" + skilllevel + ", priorityflag=" + priority_flag + ", years of exp=" +years_of_exp + ", getCreatedBy()=" + getCreatedBy()
				+ ", getCreatedDate()=" + getCreatedDate()
				+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedDate()="
				+ getUpdatedDate() +  "]";
	
	}
	
}
