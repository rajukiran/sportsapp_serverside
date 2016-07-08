package com.sports.core.model;

public class CoachVO extends BaseVO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int requestId;
	private long coachId;
	private long studentId;
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public long getCoachId() {
		return coachId;
	}
	public void setCoachId(long coachId) {
		this.coachId = coachId;
	}
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	

}
