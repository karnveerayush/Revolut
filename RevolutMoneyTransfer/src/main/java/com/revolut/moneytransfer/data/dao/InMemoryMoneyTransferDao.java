package com.revolut.moneytransfer.data.dao;

import com.revolut.moneytransfer.data.InMemoryDatastore;
import com.revolut.moneytransfer.exceptions.RMTDataException;
import com.revolut.moneytransfer.models.Account;

public class InMemoryMoneyTransferDao implements MoneyTransferDao {

	private InMemoryDatastore inMemoryDatastore;

	public InMemoryMoneyTransferDao(InMemoryDatastore inMemoryDatastore) {
		this.inMemoryDatastore = inMemoryDatastore;
	}

	public Account getAccountByName(String accName) throws RMTDataException {
		return inMemoryDatastore.getAccountByName(accName);
	}

	public boolean isAccountExists(String accName) throws RMTDataException {
		return inMemoryDatastore.getAccountByName(accName) != null;
	}

	public boolean isSufficientBalance(Account account, double minRequiredBalance) {
		return account != null && account.getBalance() >= minRequiredBalance;
	}
	
	public void debit(Account account, double amount) throws RMTDataException {
		inMemoryDatastore.debit(account, amount);
	}
	
	public void credit(Account account, double amount) throws RMTDataException {
		inMemoryDatastore.credit(account, amount);
	}
}
