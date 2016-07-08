package com.sports.core.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="geo_location")
public class GeoLocation extends BaseDO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int geo_location_id;
	private float geo_location_long;
	private float geo_location_latit;
	private String table_name;
	private String column_name;
	private int column_id;
	public int getGeo_location_id() {
		return geo_location_id;
	}
	public void setGeo_location_id(int geo_location_id) {
		this.geo_location_id = geo_location_id;
	}
	public float getGeo_location_long() {
		return geo_location_long;
	}
	public void setGeo_location_long(float geo_location_long) {
		this.geo_location_long = geo_location_long;
	}
	public float getGeo_location_latit() {
		return geo_location_latit;
	}
	public void setGeo_location_latit(float geo_location_latit) {
		this.geo_location_latit = geo_location_latit;
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
	@Override
	public String toString() {
		return "GeoLocation [id=" + geo_location_id + ", geoLocationLang=" + geo_location_long + ", geoLocationLatit="
				+ geo_location_latit + ", tablename=" + table_name + ", ColumnName=" + column_name + ",columnId=" +column_id+", getCreatedBy()=" + getCreatedBy()
				+ ", getCreatedDate()=" + getCreatedDate()
				+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedDate()="
				+ getUpdatedDate() + "]";
	
	}
}
