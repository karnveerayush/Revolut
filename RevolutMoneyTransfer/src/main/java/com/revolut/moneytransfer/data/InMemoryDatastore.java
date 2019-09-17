package com.revolut.moneytransfer.data;

import java.util.HashMap;
import java.util.Map;

import com.revolut.moneytransfer.consts.Constants;
import com.revolut.moneytransfer.exceptions.RMTDataException;
import com.revolut.moneytransfer.models.Account;
import com.revolut.moneytransfer.utils.DataUtil;

public class InMemoryDatastore {

	private Map<String, Account> accounts;

	private static InMemoryDatastore INMMEMORY_DATASTORE = null;

	private InMemoryDatastore(int numOfAccounts) {
		this.accounts = new HashMap<String, Account>();
		populateData(numOfAccounts);
	}

	private void populateData(int numOfAccounts) {
		int baseAccRng = 9000;
		for (int j = 1; j <= numOfAccounts; j++) {
			Account account = DataUtil.createAccount(baseAccRng++, "Account" + j, 1000.0, "Person" + j,
					Constants.BANK_NAME);
			this.accounts.put("Account" + j, account);
		}

		printAccounts();
	}

	public void printAccounts() {
		for (String a : accounts.keySet()) {
			System.out.println(accounts.get(a));
		}
	}

	public static InMemoryDatastore getInstance(int numberOfAccounts) {
		if (INMMEMORY_DATASTORE == null) {
			synchronized (InMemoryDatastore.class) {
				if (INMMEMORY_DATASTORE == null) {
					INMMEMORY_DATASTORE = new InMemoryDatastore(numberOfAccounts);
				}
			}
		}
		return INMMEMORY_DATASTORE;
	}

	public Account getAccountByName(String name) throws RMTDataException {
		Account account = this.accounts.get(name);
		if(account == null)
			throw new RMTDataException(Constants.ACCOUNT_NOT_FOUND);
		return account;
	}
	
	public void debit(Account account, double amount) throws RMTDataException {
		account.debit(amount);
	}
	
	public void credit(Account account, double amount) throws RMTDataException {
		account.credit(amount);
	}
}
