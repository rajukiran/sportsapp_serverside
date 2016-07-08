package com.sports.core.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "attachement")
public class Attachments extends BaseDO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int attach_id;
	private String attach_desc;
	private String table_name;
	private String column_name;
	private int column_id;
	private String file_name;
	private byte[] file_data;
	private String file_extension;
	public int getAttach_id() {
		return attach_id;
	}
	public void setAttach_id(int attach_id) {
		this.attach_id = attach_id;
	}
	public String getAttach_desc() {
		return attach_desc;
	}
	public void setAttach_desc(String attach_desc) {
		this.attach_desc = attach_desc;
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
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public byte[] getFile_data() {
		return file_data;
	}
	public void setFile_data(byte[] file_data) {
		this.file_data = file_data;
	}
	public String getFile_extension() {
		return file_extension;
	}
	public void setFile_extension(String file_extension) {
		this.file_extension = file_extension;
	}
	@Override
	public String toString() {
		return "Attachments [id=" + attach_id + ", AttachDesc=" + attach_desc + ", filename="
				+ file_name + ", fileExtension=" + file_extension +", tablename=" + table_name + ", ColumnName=" + column_name + ",columnId=" +column_id+", getCreatedBy()=" + getCreatedBy()
				+ ", getCreatedDate()=" + getCreatedDate()
				+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedDate()="
				+ getUpdatedDate()  + "]";
	
	}
}
