package com.piggybank.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import com.piggybank.utilities.ConnectionUtility;
import com.piggybank.daos.AccountsDAO;
import com.piggybank.models.Accounts;
import com.piggybank.models.Users;

public class AccountsDAOImpl implements AccountsDAO {

	@Override
	public boolean createAccount(String type) {
		try(Connection conn = ConnectionUtility.getConnection()) {
			String sql0 = "SELECT acctid FROM account ORDER BY acctid DESC LIMIT 1;";
			Statement statement0 = conn.createStatement();
			ResultSet result0 = statement0.executeQuery(sql0);
			int acctID = 0;
			while (result0.next()) {
				acctID = result0.getInt("acctid") + 1;
			}
			
			Users user = new Users();
			String sql = "INSERT INTO account VALUES ('" + acctID + "', 0, 'Pending', '" + type + "', '" + user.getUserId() + "', NULL);";
			
			Statement statement = conn.createStatement();
			int i = statement.executeUpdate(sql);
			
			if (i > 0) {
				Accounts account = new Accounts();
				account.setAccountId(acctID);
				account.setBalance(0);
				account.setStatus("Pending");
				account.setType(type);
				
				System.out.println("Success!");
				return true;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
}
