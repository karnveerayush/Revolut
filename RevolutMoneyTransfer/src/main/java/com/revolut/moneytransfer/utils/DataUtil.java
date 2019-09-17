package com.revolut.moneytransfer.utils;

import com.revolut.moneytransfer.models.Account;

public final class DataUtil {
	private DataUtil() {

	}

	public static Account createAccount(int id, String name, double initialBalance, String accountHolderName,
			String bankName) {
		Account account = new Account(id, name, accountHolderName, bankName);
		account.credit(initialBalance);
		return account;
	}
}
