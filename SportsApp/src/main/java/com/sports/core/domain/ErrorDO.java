package com.sports.core.domain;

import java.io.Serializable;

public class ErrorDO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errCode;
	private String errDesc;
	private String errType;
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrDesc() {
		return errDesc;
	}
	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
	}
	public String getErrType() {
		return errType;
	}
	public void setErrType(String errType) {
		this.errType = errType;
	}
	@Override
	public String toString() {
		return "ErrorDO [errCode=" + errCode + ", errDesc=" + errDesc
				+ ", errType=" + errType + "]";
	}
	
	
}
