package com.sports.core.model;

import javax.persistence.Transient;

import com.sports.core.model.BaseVO;

public class RequestsVO extends BaseVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private int requestId;
	
	private long senderId;
	private String senderFirstName;
	private String senderLastName;
	private String receiverFirstName;
	private String receiverLastName;
	private int senderRoleId;
	private int receiverRoleId;
	private String senderRoleName;
	private String receiverRoleName;
	private long parentId;
	private String parentFirstName;
	private String parentLastName;
	private long[] childId;
	private long receiverChildId;
	private long childsId;
	private String childFirstName;
	private String childLastName;
	
	
	public long getChildsId() {
		return childsId;
	}
	public void setChildsId(long childsId) {
		this.childsId = childsId;
	}
	public String getChildFirstName() {
		return childFirstName;
	}
	public void setChildFirstName(String childFirstName) {
		this.childFirstName = childFirstName;
	}
	public String getChildLastName() {
		return childLastName;
	}
	public void setChildLastName(String childLastName) {
		this.childLastName = childLastName;
	}
	public long getReceiverChildId() {
		return receiverChildId;
	}
	public void setReceiverChildId(long receiverChildId) {
		this.receiverChildId = receiverChildId;
	}
	public long[] getChildId() {
		return childId;
	}
	public void setChildId(long[] childId) {
		this.childId = childId;
	}
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
	public String getSenderRoleName() {
		return senderRoleName;
	}
	public void setSenderRoleName(String senderRoleName) {
		this.senderRoleName = senderRoleName;
	}
	public String getReceiverRoleName() {
		return receiverRoleName;
	}
	public void setReceiverRoleName(String receiverRoleName) {
		this.receiverRoleName = receiverRoleName;
	}
	public int getReceiverRoleId() {
		return receiverRoleId;
	}
	public void setReceiverRoleId(int receiverRoleId) {
		this.receiverRoleId = receiverRoleId;
	}
	public int getSenderRoleId() {
		return senderRoleId;
	}
	public void setSenderRoleId(int senderRoleId) {
		this.senderRoleId = senderRoleId;
	}
	public String getReceiverFirstName() {
		return receiverFirstName;
	}
	public void setReceiverFirstName(String receiverFirstName) {
		this.receiverFirstName = receiverFirstName;
	}
	public String getReceiverLastName() {
		return receiverLastName;
	}
	public void setReceiverLastName(String receiverLastName) {
		this.receiverLastName = receiverLastName;
	}
	public String getSenderFirstName() {
		return senderFirstName;
	}
	public void setSenderFirstName(String senderFirstName) {
		this.senderFirstName = senderFirstName;
	}
	public String getSenderLastName() {
		return senderLastName;
	}
	public void setSenderLastName(String senderLastName) {
		this.senderLastName = senderLastName;
	}
	private long receiverId;
	private String status;
	private String message;
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public long getSenderId() {
		return senderId;
	}
	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}
	public long getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(long receiverId) {
		this.receiverId = receiverId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	//private FamilyDetails[] family;
	
}
