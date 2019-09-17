package com.revolut.moneytransfer.models;

public class MoneyTransferRequest {

	private String sourceAccount;
	private String targetAccount;
	private double transactionAmount;

	public MoneyTransferRequest(String sourceAccount, String targetAccount, double transactionAmount) {
		this.sourceAccount = sourceAccount;
		this.targetAccount = targetAccount;
		this.transactionAmount = transactionAmount;
	}

	public String getSourceAccount() {
		return sourceAccount;
	}

	public void setSourceAccount(String sourceAccount) {
		this.sourceAccount = sourceAccount;
	}

	public String getTargetAccount() {
		return targetAccount;
	}

	public void setTargetAccount(String targetAccount) {
		this.targetAccount = targetAccount;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

}
