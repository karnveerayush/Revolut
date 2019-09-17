package com.revolut.moneytransfer.services;

import org.junit.Before;
import org.junit.Test;

import com.revolut.moneytransfer.data.InMemoryDatastore;
import com.revolut.moneytransfer.data.dao.InMemoryMoneyTransferDao;
import com.revolut.moneytransfer.data.dao.MoneyTransferDao;
import com.revolut.moneytransfer.exceptions.RMTException;
import com.revolut.moneytransfer.models.MoneyTransferRequest;

public class MoneyTransferServiceTest {

	private MoneyTransferService moneyTransferService;

	@Before
	public void setupClass() {
		MoneyTransferDao moneyTransferDao = new InMemoryMoneyTransferDao(InMemoryDatastore.getInstance(5));
		moneyTransferService = new DefaultMoneyTransferService(moneyTransferDao);
	}

	@Test
	public void testTransact_validInput() throws RMTException {
		moneyTransferService.transact(new MoneyTransferRequest("Account1", "Account2", 30));
	}

	@Test
	public void testTransact_edge_transactionAmountSameAsBalance() throws RMTException {
		moneyTransferService.transact(new MoneyTransferRequest("Account3", "Account4", 100));
	}

	@Test(expected = RMTException.class)
	public void testTransact_invalidSourceAccount() throws RMTException {
		moneyTransferService.transact(new MoneyTransferRequest("Junk", "Account2", 30));
	}

	@Test(expected = RMTException.class)
	public void testTransact_invalidTargetAccount() throws RMTException {
		moneyTransferService.transact(new MoneyTransferRequest("Account1", "Junk", 30));
	}

	@Test(expected = RMTException.class)
	public void testTransact_insufficientBalance() throws RMTException {
		moneyTransferService.transact(new MoneyTransferRequest("Account1", "Account2", 300));
	}

	@Test(expected = RMTException.class)
	public void testTransact_sameSourceAndTarget() throws RMTException {
		moneyTransferService.transact(new MoneyTransferRequest("Account3", "Account3", 10));
	}
}
