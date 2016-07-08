package com.sports.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address extends BaseDO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int addr_id;
	@Column(name="addr_type_1")
	private String address_type_1;
	@Column(name="addr_type_2")
	private String address_type_2;
	@Column(name="pin_code")
	private String pincode;
	private String table_name;
	private String column_name;
	@Column(name="column_id")
	private int column_id;
	@OneToOne
	@JoinColumn(name="continent_id")
	private Continent continent;
	@OneToOne
	@JoinColumn(name="country_id")
	private Country country;
	@OneToOne
	@JoinColumn(name="state_id")
	private State state;
	public int getAddr_id() {
		return addr_id;
	}
	public void setAddr_id(int addr_id) {
		this.addr_id = addr_id;
	}
	public String getAddress_type_1() {
		return address_type_1;
	}
	public void setAddress_type_1(String address_type_1) {
		this.address_type_1 = address_type_1;
	}
	public String getAddress_type_2() {
		return address_type_2;
	}
	public void setAddress_type_2(String address_type_2) {
		this.address_type_2 = address_type_2;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getColumn_name() {
		return column_name;
	}
	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}
	public int getColumn_id() {
		return column_id;
	}
	public void setColumn_id(int column_id) {
		this.column_id = column_id;
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
	
	@Override
	public String toString() {
		return "Address [id=" + addr_id + ", AddressType1=" + address_type_1 + ", addressType2="
				+ address_type_2 + ", pincode=" + pincode + ", tablename=" + table_name + ", ColumnName=" + column_name + ",columnId=" +column_id+", getCreatedBy()=" + getCreatedBy()
				+ ", getCreatedDate()=" + getCreatedDate()
				+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedDate()="
				+ getUpdatedDate()  + "]";
	
	}
}
