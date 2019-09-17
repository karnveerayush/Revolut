package com.revolut.moneytransfer.main;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

import com.revolut.moneytransfer.consts.Constants;
import com.revolut.moneytransfer.consts.TransactionState;
import com.revolut.moneytransfer.models.MoneyTransferResponse;
import com.revolut.moneytransfer.utils.ProcessUtil;

public class RMTClient {

	@Test
	public void testMoneyTransfer_happyPath() throws IOException {
		String URL = "http://localhost:8080/RevolutMoneyTransfer/Accounts/Transfer/Account1/Account2/30";

		URL url = new URL(URL);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod("GET");

		System.out.println("Response code : " + conn.getResponseCode());

		MoneyTransferResponse response = ProcessUtil.processResponse(conn);
		
		Assert.assertEquals(TransactionState.COMPLETED, response.getState());
		Assert.assertEquals(Constants.COMPLETE_TRANSACTION, response.getMessage());
	}
	
	@Test
	public void testMoneyTransfer_invalidPath_wrongSourceAccount() throws IOException {
		String URL = "http://localhost:8080/RevolutMoneyTransfer/Accounts/Transfer/Account100/Account2/30";

		URL url = new URL(URL);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod("GET");

		System.out.println("Response code : " + conn.getResponseCode());

		MoneyTransferResponse response = ProcessUtil.processResponse(conn);
		
		Assert.assertEquals(TransactionState.ERRORED, response.getState());
		Assert.assertEquals(Constants.ERROR_TRANSACTION + Constants.ACCOUNT_NOT_FOUND, response.getMessage());
	}
	
	@Test
	public void testMoneyTransfer_invalidPath_wrongTargetAccount() throws IOException {
		String URL = "http://localhost:8080/RevolutMoneyTransfer/Accounts/Transfer/Account1/Account200/30";

		URL url = new URL(URL);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod("GET");

		System.out.println("Response code : " + conn.getResponseCode());

		MoneyTransferResponse response = ProcessUtil.processResponse(conn);
		
		Assert.assertEquals(TransactionState.ERRORED, response.getState());
		Assert.assertEquals(Constants.ERROR_TRANSACTION + Constants.ACCOUNT_NOT_FOUND, response.getMessage());
	}
	
	@Test
	public void testMoneyTransfer_invalidPath_insufficientBalance() throws IOException {
		String URL = "http://localhost:8080/RevolutMoneyTransfer/Accounts/Transfer/Account1/Account2/3000";

		URL url = new URL(URL);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod("GET");

		System.out.println("Response code : " + conn.getResponseCode());

		MoneyTransferResponse response = ProcessUtil.processResponse(conn);
		
		Assert.assertEquals(TransactionState.ERRORED, response.getState());
		Assert.assertEquals(Constants.ERROR_TRANSACTION + Constants.INSUFFICIENT_BALANCE, response.getMessage());
	}
	
	@Test
	public void testMoneyTransfer_invalidPath_sameSourceAndTarget() throws IOException {
		String URL = "http://localhost:8080/RevolutMoneyTransfer/Accounts/Transfer/Account1/Account1/30";

		URL url = new URL(URL);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod("GET");

		System.out.println("Response code : " + conn.getResponseCode());

		MoneyTransferResponse response = ProcessUtil.processResponse(conn);
		
		Assert.assertEquals(TransactionState.ERRORED, response.getState());
		Assert.assertEquals(Constants.ERROR_TRANSACTION + Constants.WRONG_INPUT_SOURCE_TARGET_SAME, response.getMessage());
	}
}
