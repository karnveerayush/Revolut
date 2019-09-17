package com.revolut.moneytransfer.apis;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.revolut.moneytransfer.consts.Constants;
import com.revolut.moneytransfer.consts.TransactionState;
import com.revolut.moneytransfer.data.InMemoryDatastore;
import com.revolut.moneytransfer.data.dao.InMemoryMoneyTransferDao;
import com.revolut.moneytransfer.data.dao.MoneyTransferDao;
import com.revolut.moneytransfer.exceptions.RMTException;
import com.revolut.moneytransfer.models.MoneyTransferRequest;
import com.revolut.moneytransfer.models.MoneyTransferResponse;
import com.revolut.moneytransfer.services.DefaultMoneyTransferService;
import com.revolut.moneytransfer.services.MoneyTransferService;

@Path("/RevolutMoneyTransfer")
public class RevolutMoneyTransferController {

	private MoneyTransferService moneyTransferService;
	private final int numOfAccounts = 5;

	public RevolutMoneyTransferController() {
		MoneyTransferDao moneyTransferDao = new InMemoryMoneyTransferDao(InMemoryDatastore.getInstance(numOfAccounts));
		moneyTransferService = new DefaultMoneyTransferService(moneyTransferDao);
	}

	public RevolutMoneyTransferController(MoneyTransferService moneyTransferService) {
		this.moneyTransferService = moneyTransferService;
	}

	@GET
	@Path("Accounts/Transfer/{sourceAccName}/{targetAccName}/{transferAmount}")
	@Produces(MediaType.APPLICATION_JSON)
	public MoneyTransferResponse transferMoney(@PathParam("sourceAccName") String sourceAccount,
			@PathParam("targetAccName") String targetAccount, @PathParam("transferAmount") double transactionAmount) {
		MoneyTransferResponse response = new MoneyTransferResponse();
		try {
			System.out.printf("Input: %s %s %f", sourceAccount, targetAccount, transactionAmount);
			System.out.println();
			MoneyTransferRequest request = new MoneyTransferRequest(sourceAccount, targetAccount, transactionAmount);
			moneyTransferService.transact(request);
			response.setMessage(Constants.COMPLETE_TRANSACTION);
			response.setState(TransactionState.COMPLETED);
		} catch (RMTException e) {
			e.printStackTrace();
			response.setMessage(Constants.ERROR_TRANSACTION + e.getMessage());
			response.setState(TransactionState.ERRORED);
		}
		
		return response;
	}
}
