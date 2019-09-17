package com.revolut.moneytransfer.exceptions;

public class RMTDataException extends RMTException {

	private static final long serialVersionUID = 5814223247588327104L;
	
	public RMTDataException(String message) {
		super(message);
	}

	public RMTDataException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
