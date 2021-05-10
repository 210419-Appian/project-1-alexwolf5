package com.piggybank.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.piggybank.utilities.ConnectionUtility;
import com.piggybank.daos.AccountsDAO;
import com.piggybank.models.Accounts;
import com.piggybank.models.Users;

public class AccountsDAOImpl implements AccountsDAO {

	@Override
	public void createAccount(String type, int UserId) {
		try(Connection conn = ConnectionUtility.getConnection()) {
			String sql0 = "SELECT acctid FROM account ORDER BY acctid DESC LIMIT 1;";
			Statement statement0 = conn.createStatement();
			ResultSet result0 = statement0.executeQuery(sql0);
			int acctID = 0;
			while (result0.next()) {
				acctID = result0.getInt("acctid") + 1;
			}
			
			Users user = new Users();
			String sql = "INSERT INTO account VALUES (" + acctID + ", 0, 'Pending', '" + type + "', " + UserId + ");";
			
			Statement statement = conn.createStatement();
			int i = statement.executeUpdate(sql);
/*			
			if (i > 0) {
				Accounts account = new Accounts();
				account.setAccountId(acctID);
				account.setBalance(0);
				account.setStatus("Pending");
				account.setType(type);

			}
*/			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public boolean updateBalance(int acctId, double balance) {
		try(Connection conn = ConnectionUtility.getConnection()) {
			String sql = "UPDATE account SET acctbalance = " + balance + " WHERE acctid = " + acctId + ";";
			Statement statement = conn.createStatement();
			int i = statement.executeUpdate(sql);
			if (i > 0) {
				Accounts acct = new Accounts();
				acct.setBalance(balance);
				return true;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean transfer(double transferamount, int acct1Giver, int acct2Taker) {
		try(Connection conn = ConnectionUtility.getConnection()) {
			String sql = "UPDATE account SET acctbalance = acctbalance - " + transferamount + " WHERE acctid = " + acct1Giver + ";";
			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
			
			String sql2 = "UPDATE account SET acctbalance = acctbalance + " + transferamount + " WHERE acctid = " + acct2Taker + ";";
			Statement statement2 = conn.createStatement();
			statement2.executeUpdate(sql2);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public List<Accounts> findByUserId(int userId) {
		try(Connection conn = ConnectionUtility.getConnection()) {
			String sql = "SELECT * FROM account WHERE acctholder = " + userId + ";";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			List<Accounts> list = new ArrayList<>();
			while (result.next()) {    
				Accounts acct = new Accounts();
				
				acct.setAccountId(result.getInt("acctid"));
				acct.setBalance(result.getDouble("acctbalance"));
				acct.setStatus(result.getString("acctstatus"));
				acct.setType(result.getString("accttype"));
				
				list.add(acct);

				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public double getBalance(int acctId) {
		try(Connection conn = ConnectionUtility.getConnection()) {
			String sql = "SELECT acctbalance FROM account WHERE acctid = " + acctId + ";";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {    
				return result.getDouble("acctbalance");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getAcctId(int UserId) {
		try(Connection conn = ConnectionUtility.getConnection()) {
			String sql = "SELECT acctid FROM account WHERE acctholder = " + UserId + ";";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {    
				return result.getInt("acctid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Accounts> everyAcct() {
		try(Connection conn = ConnectionUtility.getConnection()) {
			String sql = "SELECT * FROM account;";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			List<Accounts> list = new ArrayList<>();
			while (result.next()) {
				Accounts acct = new Accounts();
				
				acct.setAccountId(result.getInt("acctid"));
				acct.setBalance(result.getDouble("acctbalance"));
				acct.setStatus(result.getString("acctstatus"));
				acct.setType(result.getString("accttype"));
				
				list.add(acct);
			}
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
