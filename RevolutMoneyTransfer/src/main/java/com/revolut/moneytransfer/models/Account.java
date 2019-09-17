package com.revolut.moneytransfer.models;

import com.revolut.moneytransfer.consts.Constants;
import com.revolut.moneytransfer.exceptions.RMTDataException;

public class Account {

	private String name;
	private int id;
	private double balance;
	private String accountHolderName;
	private String bankName;

	public Account(int id, String name, String accountHolderName, String bankName) {
		this.id = id;
		this.name = name;
		this.balance = 0;
		this.accountHolderName = accountHolderName;
		this.bankName = bankName;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountHolderName == null) ? 0 : accountHolderName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((bankName == null) ? 0 : bankName.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountHolderName == null) {
			if (other.accountHolderName != null)
				return false;
		} else if (!accountHolderName.equals(other.accountHolderName))
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (bankName == null) {
			if (other.bankName != null)
				return false;
		} else if (!bankName.equals(other.bankName))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [name=" + name + ", id=" + id + ", balance=" + balance + ", accountHolderName="
				+ accountHolderName + ", bankName=" + bankName + "]";
	}

	public void credit(double amount) {
		if (amount <= 0)
			throw new IllegalArgumentException(Constants.WRONG_INPUT_AMOUNT);

		this.balance += amount;
	}

	public void debit(double amount) throws RMTDataException {
		if (amount <= 0)
			throw new IllegalArgumentException(Constants.WRONG_INPUT_AMOUNT);

		if (balance < amount)
			throw new RMTDataException(Constants.INSUFFICIENT_BALANCE);

		this.balance -= amount;
	}
}
