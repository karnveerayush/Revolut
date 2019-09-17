package com.revolut.moneytransfer.exceptions;

public class RMTException extends Exception {

	private static final long serialVersionUID = 5969182271330390255L;
	
	public RMTException(String message) {
		super(message);
	}

	public RMTException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
