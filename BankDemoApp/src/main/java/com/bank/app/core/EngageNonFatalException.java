package com.bank.app.core;

public class EngageNonFatalException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public EngageNonFatalException() {
		super();
		// TODO Auto-generated constructor stub
	}


	public EngageNonFatalException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public EngageNonFatalException(String message, Integer intMessage, Exception ex) {
		super(intMessage.toString());
	}

	public EngageNonFatalException(String message, Integer intMessage) {
		super(intMessage.toString());
	}

	
}
