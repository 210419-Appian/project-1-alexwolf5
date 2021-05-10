package com.piggybank.services;

import com.piggybank.daos.UsersDAO;
import com.piggybank.daos.UsersDAOImpl;
import com.piggybank.models.Users;

public class UserService {

	static UsersDAO udao = new UsersDAOImpl();
	
	public static Users findByUsername(String username) {
		return udao.findByUsername(username);
	}
	
	public int loggingIn(String username, String password) {
		return udao.loggingIn(username, password);
	}
	
	public int checkUsernameAvailability(String username) {
		return udao.checkUsernameAvailability(username);
	}
	
	public void register(String username, String password, String firstname, String lastname, String email) {
		udao.register(username, password, firstname, lastname, email);
	}
	
	public String getRole(int userId) {
		return udao.getRole(userId);
	}
	
	public void newDetails (int userId, String username, String password, String firstname, String lastname, String email, String role) {
		udao.newDetails(userId, username, password, firstname, lastname, email, role);
	}
}
