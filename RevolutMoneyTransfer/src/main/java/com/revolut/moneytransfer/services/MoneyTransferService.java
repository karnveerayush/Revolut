package com.revolut.moneytransfer.services;

import com.revolut.moneytransfer.exceptions.RMTException;
import com.revolut.moneytransfer.models.MoneyTransferRequest;

public interface MoneyTransferService {

	void transact(MoneyTransferRequest moneyTransferRequest) throws RMTException;

}