package com.piggybank.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			
			if (i > 0) {
				Users user = new Users();
				user.setUserId(userID);
				user.setFirstName(firstname);
				user.setLastName(lastname);
				user.setRole("User");
				user.setEmail(email);
				user.setUsername(username);
				user.setPassword(password);
				
				System.out.println("Success!");
				return ;
			}
			else {
				System.out.println("Failed!");
				return;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	@Override
	public int loggingIn(String username, String password) {
		int userID = 0;
		try(Connection conn = ConnectionUtility.getConnection()) {
			String sql = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "';";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()) {
				Users user = new Users();
				user.setUserId(result.getInt("userid"));
				user.setFirstName(result.getString("firstname"));
				user.setLastName(result.getString("lastname"));
				user.setRole(result.getString("role"));
				user.setEmail(result.getString("email"));
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("password"));
			}
			
			return userID;
			
	} catch(SQLException e) {
		e.printStackTrace();
	}
		return userID;
	}

	@Override
	public void newUsername(int userId, String username) {
		try(Connection conn = ConnectionUtility.getConnection()) {
			String sql = "UPDATE users SET username = '" + username + "' WHERE userid = " + userId + ";";
			Statement statement = conn.createStatement();
			int i = statement.executeUpdate(sql);
			if (i > 0) {
				Users user = new Users();
				user.setUsername(username);
				return;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		
	}

	@Override
	public void newPassword(int userId, String password) {
		try(Connection conn = ConnectionUtility.getConnection()) {
			String sql = "UPDATE users SET username = '" + password + "' WHERE userid = " + userId + ";";
			Statement statement = conn.createStatement();
			int i = statement.executeUpdate(sql);
			if (i > 0) {
				Users user = new Users();
				user.setPassword(password);
				return;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void newFirstname(int userId, String firstname) {
		try(Connection conn = ConnectionUtility.getConnection()) {
			String sql = "UPDATE users SET username = '" + firstname + "' WHERE userid = " + userId + ";";
			Statement statement = conn.createStatement();
			int i = statement.executeUpdate(sql);
			if (i > 0) {
				Users user = new Users();
				user.setFirstName(firstname);
				return;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void newLastname(int userId, String lastname) {
		try(Connection conn = ConnectionUtility.getConnection()) {
			String sql = "UPDATE users SET username = '" + lastname + "' WHERE userid = " + userId + ";";
			Statement statement = conn.createStatement();
			int i = statement.executeUpdate(sql);
			if (i > 0) {
				Users user = new Users();
				user.setLastName(lastname);
				return;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void newEmail(int userId, String email) {
		try(Connection conn = ConnectionUtility.getConnection()) {
			String sql = "UPDATE users SET username = '" + email + "' WHERE userid = " + userId + ";";
			Statement statement = conn.createStatement();
			int i = statement.executeUpdate(sql);
			if (i > 0) {
				Users user = new Users();
				user.setEmail(email);
				return;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
