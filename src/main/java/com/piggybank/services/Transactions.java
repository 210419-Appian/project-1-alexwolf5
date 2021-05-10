package com.piggybank.services;

import com.piggybank.daos.AccountsDAO;
import com.piggybank.daos.AccountsDAOImpl;
import com.piggybank.models.Accounts;

public class Transactions {

	public double deposit(double balance, double deposit, int acctId) {
		
		balance += deposit;
		AccountsDAO dep = new AccountsDAOImpl();
		dep.updateBalance(acctId, balance);
		return balance;
	}
	
	public double withdraw(double balance, double withdraw, int acctId) {
		if (withdraw < balance) {
			
			balance -= withdraw;
			AccountsDAO with = new AccountsDAOImpl();
			with.updateBalance(acctId, balance);
			return balance;
		} else {
			return 0;
		}
	}
	
	public void transfer(double balance1, double balance2, double transfer, int acctIdDec, int acctIdInc) {
		
		if (transfer < balance1) {
			
			balance1 -= transfer;
			balance2 += transfer;
			AccountsDAO tran = new AccountsDAOImpl();
			tran.updateBalance(acctIdDec, balance1);
			tran.updateBalance(acctIdInc, balance2);
			return;
		} else {
			return;
		}
	}
}
