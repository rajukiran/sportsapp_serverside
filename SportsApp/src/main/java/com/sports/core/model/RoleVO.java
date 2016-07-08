package com.sports.core.model;
//import java.sql.Date;
//import java.time.LocalDate;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
//import javax.persistence.SecondaryTable;
import javax.persistence.Table;


//import javax.validation.constraints.Size;

//import org.hibernate.validator.constraints.NotEmpty;

/**
* Entity bean with JPA annotations
* Hibernate provides JPA implementation
* @author pankaj
*
*/
@Entity
@Table(name="ROLE")
public class RoleVO extends BaseVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="role_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int roleId;
	private String roleName;
	private String roleDesc;
	private String StatusFlag;
	
	
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public String getStatusFlag() {
		return StatusFlag;
	}
	public void setStatusFlag(String statusFlag) {
		StatusFlag = statusFlag;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
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
	
	
}
