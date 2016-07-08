package com.sports.core.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="geo_location")
public class GeoLocationVO extends BaseVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int geoLocationId;
	private float geoLocationLong;
	private float geoLocationLatit;
	private String tableName;
	private String columnName;
	private int columnId;
	
	public int getGeoLocationId() {
		return geoLocationId;
	}

	public void setGeoLocationId(int geoLocationId) {
		this.geoLocationId = geoLocationId;
	}

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

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public int getColumnId() {
		return columnId;
	}

	public void setColumnId(int columnId) {
		this.columnId = columnId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "GeoLocation [id=" + geoLocationId + ", geoLocationLang=" + geoLocationLong + ", geoLocationLatit="
				+ geoLocationLatit + ", tablename=" + tableName + ", ColumnName=" + columnName + ",columnId=" +columnId+", getCreatedBy()=" + getCreatedBy()
				+ ", getCreatedDate()=" + getCreatedDate()
				+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedDate()="
				+ getUpdatedDate() + ", getErrorDO()="
				+ getErrorVO() + "]";
	
	}
}
