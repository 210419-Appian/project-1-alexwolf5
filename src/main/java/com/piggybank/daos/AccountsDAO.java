package com.piggybank.daos;

import com.piggybank.daos.AccountsDAO;

public interface AccountsDAO {
	
	public boolean createAccount(String type);
	public boolean updateBalance(int acctId, double balance);
	public boolean transfer(double transferamount, int acct1Giver, int acct2Taker);
}
