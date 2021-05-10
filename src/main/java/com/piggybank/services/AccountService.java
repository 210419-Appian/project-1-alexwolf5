package com.piggybank.services;

import java.util.List;

import com.piggybank.daos.AccountsDAO;
import com.piggybank.daos.AccountsDAOImpl;
import com.piggybank.models.Accounts;

public class AccountService {

	static AccountsDAO adao = new AccountsDAOImpl();
	
	public static List<Accounts> findByUserId(int userId) {
		
		return adao.findByUserId(userId);
	}
	
	public void createAccount(String type, int UserId) {
		adao.createAccount(type, UserId);
	}
	
	public double getBalance(int acctId) {
		return adao.getBalance(acctId);
	}
	
	public int getAcctId(int UserId) {
		return adao.getAcctId(UserId);
	}
}
