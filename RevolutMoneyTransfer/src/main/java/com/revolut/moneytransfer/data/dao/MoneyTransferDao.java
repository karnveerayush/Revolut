package com.revolut.moneytransfer.data.dao;

import com.revolut.moneytransfer.exceptions.RMTDataException;
import com.revolut.moneytransfer.models.Account;

public interface MoneyTransferDao {

	boolean isAccountExists(String accName) throws RMTDataException;

	boolean isSufficientBalance(Account account, double minRequiredBalance);

	void credit(Account account, double amount) throws RMTDataException;

	void debit(Account account, double amount) throws RMTDataException;

	Account getAccountByName(String accName) throws RMTDataException;
}
