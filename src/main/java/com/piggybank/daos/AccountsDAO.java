package com.piggybank.daos;

import java.util.List;

import com.piggybank.daos.AccountsDAO;
import com.piggybank.models.Accounts;

public interface AccountsDAO {
	
	public void createAccount(String type, int UserId);
	public boolean updateBalance(int acctId, double balance);
	public boolean transfer(double transferamount, int acct1Giver, int acct2Taker);
	public List<Accounts> findByUserId(int userId);
}
