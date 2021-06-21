package com.bank.app.core;

public class EngageFatalException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public EngageFatalException() {
		super();
		// TODO Auto-generated constructor stub
	}


	public EngageFatalException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public EngageFatalException(String message, Integer intMessage, Exception ex) {
		super(intMessage.toString());
	}

	public EngageFatalException(String message, Integer intMessage) {
		super(intMessage.toString());
	}

	
}
