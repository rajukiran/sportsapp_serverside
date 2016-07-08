package com.sports.core.exception;

public class SportsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "SportsException []";
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}

	public SportsException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SportsException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public SportsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public SportsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public SportsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
