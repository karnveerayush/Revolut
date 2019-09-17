package com.revolut.moneytransfer.services;

import java.util.concurrent.locks.ReentrantLock;

import com.revolut.moneytransfer.consts.Constants;
import com.revolut.moneytransfer.data.dao.MoneyTransferDao;
import com.revolut.moneytransfer.exceptions.RMTDataException;
import com.revolut.moneytransfer.exceptions.RMTException;
import com.revolut.moneytransfer.models.Account;
import com.revolut.moneytransfer.models.MoneyTransferRequest;
import com.revolut.moneytransfer.utils.ValidationUtil;

public class DefaultMoneyTransferService implements MoneyTransferService {

	private MoneyTransferDao moneyTransferDao;
	private ReentrantLock LOCK;

	public DefaultMoneyTransferService(MoneyTransferDao moneyTransferDao) {
		this.moneyTransferDao = moneyTransferDao;
		LOCK = new ReentrantLock();
	}

	public void transact(MoneyTransferRequest moneyTransferRequest) throws RMTException {
		try {
			ValidationUtil.validateTransactionInput(moneyTransferRequest);

			if (moneyTransferDao.isAccountExists(moneyTransferRequest.getSourceAccount())
					&& this.moneyTransferDao.isAccountExists(moneyTransferRequest.getTargetAccount())) {
				Account source = moneyTransferDao.getAccountByName(moneyTransferRequest.getSourceAccount());

				LOCK.lock();
				if (moneyTransferDao.isSufficientBalance(source, moneyTransferRequest.getTransactionAmount())) {
					Account target = moneyTransferDao.getAccountByName(moneyTransferRequest.getTargetAccount());

					transactSecurely(moneyTransferRequest.getTransactionAmount(), source, target);
				} else {
					throw new RMTDataException(Constants.INSUFFICIENT_BALANCE);
				}
				LOCK.unlock();
			} else {
				throw new RMTDataException(Constants.ACCOUNT_NOT_FOUND);
			}

		} catch (RMTDataException ex) {
			throw ex;
		} catch (RMTException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new RMTException(ex.getMessage(), ex);
		} finally {
			if (LOCK.isHeldByCurrentThread()) {
				LOCK.unlock();
			}
		}
	}

	private void transactSecurely(double transactionAmount, Account source, Account target) throws RMTException {
		double srcBalBeforeTxn = source.getBalance();
		double tgtBalBeforeTxn = target.getBalance();
		try {
			if (moneyTransferDao.isAccountExists(source.getName())
					&& moneyTransferDao.isAccountExists(target.getName())) {
				moneyTransferDao.debit(source, transactionAmount);
				moneyTransferDao.credit(target, transactionAmount);
			} else {
				throw new RMTDataException(Constants.ACCOUNT_NOT_FOUND);
			}
		} catch (Exception ex) {
			source.setBalance(srcBalBeforeTxn);
			target.setBalance(tgtBalBeforeTxn);
			throw new RMTException(ex.getMessage(), ex);
		}
	}
}
