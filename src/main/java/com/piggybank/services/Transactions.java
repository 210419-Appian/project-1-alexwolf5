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
		
		double checkBalance = balance;
		checkBalance -= withdraw;
		if (checkBalance >= 0) {
			
			balance -= withdraw;
			AccountsDAO with = new AccountsDAOImpl();
			with.updateBalance(acctId, balance);
			return balance;
		}
		
		else {
			return 0;
		}
	}
	
	public double transfer(double balance, double transfer, int acctIdDec, int acctIdInc) {
		
		double checkBalance = balance;
		checkBalance -= transfer;
		if (checkBalance >= 0) {
			AccountsDAO tran = new AccountsDAOImpl();
			tran.transfer(transfer, acctIdDec, acctIdInc);
			balance -= transfer;
			return balance;
		}
		else {
		return 0;
		}
	}
}
