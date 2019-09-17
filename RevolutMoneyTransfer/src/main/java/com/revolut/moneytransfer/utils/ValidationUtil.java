package com.revolut.moneytransfer.utils;

import com.revolut.moneytransfer.consts.Constants;
import com.revolut.moneytransfer.exceptions.RMTException;
import com.revolut.moneytransfer.models.MoneyTransferRequest;

public final class ValidationUtil {

	private ValidationUtil() {

	}
	
	public static void validateTransactionInput(MoneyTransferRequest moneyTransferRequest)
			throws RMTException {
		if(isNull(moneyTransferRequest.getSourceAccount(), moneyTransferRequest.getTargetAccount())) {
			throw new RMTException(Constants.WRONG_INPUT_NULL);
		}
		
		if(isSameAccount(moneyTransferRequest.getSourceAccount(), moneyTransferRequest.getTargetAccount())) {
			throw new RMTException(Constants.WRONG_INPUT_SOURCE_TARGET_SAME);
		}
		
		if(!isValidTransactionAmount(moneyTransferRequest.getTransactionAmount())) {
			throw new RMTException(Constants.WRONG_INPUT_AMOUNT);
		}
	}

	public static boolean isNull(String acc1Name, String acc2Name) {
		return acc1Name == null || acc2Name == null;
	}

	public static boolean isSameAccount(String acc1Name, String acc2Name) {
		return acc1Name.equals(acc2Name);
	}
	
	public static boolean isValidTransactionAmount(double transactionAmount) {
		return transactionAmount > 0;
	}
}
