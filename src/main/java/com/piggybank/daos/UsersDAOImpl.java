package com.piggybank.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.piggybank.models.Accounts;
import com.piggybank.models.Users;
import com.piggybank.utilities.ConnectionUtility;

public class UsersDAOImpl implements UsersDAO {

	@Override
	public void register(String username, String password, String firstname, String lastname, String email) {
		try(Connection conn = ConnectionUtility.getConnection()){

			String sql0 = "SELECT userid FROM users ORDER BY userid DESC LIMIT 1;";
			Statement statement0 = conn.createStatement();
			ResultSet result0 = statement0.executeQuery(sql0);
			int userID = 0;
			while (result0.next()) {
				userID = result0.getInt("userid") + 1;
			}
			
			String sql = "INSERT INTO users VALUES ('" + userID + "', '" + username + "', '" + password + "', '" + firstname + "', '" + lastname + "', '" + email + "', 'User');";
			
			Statement statement = conn.createStatement();
			int i = statement.executeUpdate(sql);
			/*
			if (i > 0) {
				Users user = new Users();
				user.setUserId(userID);
				user.setFirstName(firstname);
				user.setLastName(lastname);
				user.setRole("User");
				user.setEmail(email);
				user.setUsername(username);
				user.setPassword(password);
			}
			*/
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int loggingIn(String username, String password) {
		int userID = 0;
		try(Connection conn = ConnectionUtility.getConnection()) {
			String sql = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "';";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()) {
				//Users user = new Users(result.getInt("userid"), result.getString("username"), result.getString("password"), result.getString("firstname"), result.getString("lastname"), result.getString("email"), result.getString("userrole"));
				Users user = new Users();
				user.setUserId(result.getInt("userid"));
				user.setFirstName(result.getString("firstname"));
				user.setLastName(result.getString("lastname"));
				user.setRole(result.getString("userrole"));
				user.setEmail(result.getString("email"));
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("password"));
				
				userID = user.getUserId();
			}
			return userID;		
	} catch(SQLException e) {
		e.printStackTrace();
	}
		return userID;
	}

	@Override
	public void newDetails(int userId, String username, String password, String firstname, String lastname, String email, String role) {
		try(Connection conn = ConnectionUtility.getConnection()) {
			String sql = "UPDATE users SET username = '" + username + "', password = '" + password + "', firstname = '" + firstname + "', lastname = '" + lastname + "', email = '" + email + "', userrole = '" + role + "' WHERE userid = " + userId + ";";
			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
			return;	
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}


	@Override
	public int checkUsernameAvailability(String username) {
		int r = 0;
		try(Connection conn = ConnectionUtility.getConnection()) {
			String sql = "SELECT username FROM users WHERE username = '" + username + "';";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			if (result.next() == false) {    
			    r = 1; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public Users findByUsername(String username) {
		try(Connection conn = ConnectionUtility.getConnection()) {
			String sql = "SELECT * FROM users WHERE username = '" + username + "';";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {    
				Users user = new Users();
				
				user.setUserId(result.getInt("userid"));
				user.setFirstName(result.getString("firstname"));
				user.setLastName(result.getString("lastname"));
				user.setEmail(result.getString("email"));
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("password"));
				user.setRole(result.getString("userrole"));
				
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getRole(int userId) {
		try(Connection conn = ConnectionUtility.getConnection()) {
			String sql = "SELECT userrole FROM users WHERE userid = " + userId + ";";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				return result.getString("userrole");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
