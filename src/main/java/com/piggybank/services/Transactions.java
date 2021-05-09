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
			//Add notification that transaction wasn't completed
			return balance;
		}
	}
	
	public double transfer(double balance, double transfer, int acctIdDec, int acctIdInc) {
		
		double checkBalance = balance;
		checkBalance -= transfer;
		if (checkBalance >= 0) {
			AccountsDAO tran = new AccountsDAOImpl();
			boolean result = tran.transfer(transfer, acctIdDec, acctIdInc);
			if (result == true) {
				
			}
			balance -= transfer;
			Accounts acct = new Accounts();
			acct.setBalance(balance); 
			return balance;
		}
		else {
		//Add notification that transaction wasn't completed
		return balance;
		}
	}
}
