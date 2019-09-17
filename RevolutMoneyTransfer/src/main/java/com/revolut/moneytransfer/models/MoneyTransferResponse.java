package com.revolut.moneytransfer.models;

import com.revolut.moneytransfer.consts.Constants;
import com.revolut.moneytransfer.consts.TransactionState;

public class MoneyTransferResponse {

	private TransactionState state;

	private String message;

	public MoneyTransferResponse() {
		super();
		this.state = TransactionState.NEW;
		this.message = Constants.NEW_TRANSACTION;
	}

	public TransactionState getState() {
		return state;
	}

	public void setState(TransactionState state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MoneyTransferResponse [state=" + state + ", message=" + message + "]";
	}

}
